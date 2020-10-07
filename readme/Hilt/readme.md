# 依赖项注入



## 使用 Hilt 实现依赖项注入

这里只是介绍了创建，其他的内容参考官网

https://developer.android.google.cn/training/dependency-injection/hilt-android#kotlin



### 添加依赖项

首先，将 `hilt-android-gradle-plugin` 插件添加到项目的根级 `build.gradle` 文件中：

```groovy
buildscript {
    ...
    dependencies {
        ...
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.28-alpha'
    }
}
```



然后，应用 Gradle 插件并在 `app/build.gradle` 文件中添加以下依赖项：

```groovy
...
apply plugin: 'kotlin-kapt'
apply plugin: 'dagger.hilt.android.plugin'

android {
    ...
}

dependencies {
    implementation "com.google.dagger:hilt-android:2.28-alpha"
    kapt "com.google.dagger:hilt-android-compiler:2.28-alpha"
}
```



> **注意**：同时使用 Hilt 和[数据绑定](https://developer.android.google.cn/topic/libraries/data-binding)的项目需要 Android Studio 4.0 或更高版本。

Hilt 使用 [Java 8 功能](https://developer.android.google.cn/studio/write/java8-support)。如需在项目中启用 Java 8，请将以下代码添加到 `app/build.gradle` 文件中：

```groovy
android {
  ...
  compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
  }
}
```





>  创建的类或文件一定要添加包名，不然会出现莫名其妙的错误。



### 构造方法创建对象

#### 无参

**创建**

```kotlin
class AnalyticsAdapter @Inject constructor(

)
```

**使用**

直接在类中定义成员变量

```kotlin
@Inject
lateinit var analytics: AnalyticsAdapter
```

注意：

> 对象的创建的生命周期，比如如果是Activity那么是在onCreate创建的，所有如果在onCreate中直接使用就会空指针

#### 有参

**创建**

```kotlin
interface Engine {
    fun on()
    fun off()
}

class ChinaEngine @Inject constructor() : Engine {
    override fun on() {
        Log.i("zrm", "ChinaEngine on")
    }

    override fun off() {
        Log.i("zrm", "ChinaEngine off")
    }
}

class ChinaCar @Inject constructor(val engine: Engine) {
    lateinit var name: String

    override fun toString(): String {
        return engine.toString()
    }
}

@Module
@InstallIn(ActivityComponent::class)//告诉Hilt 这个module属于的Component,ActivityComponent是Hilt定义好的
interface MainModule {

    @Binds
    fun bindEngine(chinaEngine: ChinaEngine): Engine

}
```

有参的是通过Module、InstallIn和Binds创建的，InstallIn代表类型，ActivityComponent代表使用在Activity中，Binds使用bindEngine方法注入参数

**使用**

```kotlin
 @Inject
    lateinit var chinaCar: ChinaCar
```

### 普通方法创建对象

**创建**‘

Provides

```kotlin
data class Dog(val name:String)

@Module
@InstallIn(ActivityComponent::class)
class DogModule
{
    @Provides
    fun provideDog()=Dog("京巴犬")
}
```

**使用**

```kotlin
@Inject
    lateinit var dog: Dog
```

### 多种方式创建同一对象

**创建**

Qualifier

```kotlin
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MadeInCN

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MadeInUSA

class AmericaEngine @Inject constructor():Engine{
    override fun on() {
        Log.i("zrm", "AmericaEngine on")
    }
    override fun off() {
        Log.i("zrm", "AmericaEngine off")
    }
}

@Module
@InstallIn(ActivityComponent::class)
class TestQualifierModule {
    @Provides
    @MadeInCN
    fun provideChinaCar():ChinaCar
    {
        return ChinaCar(ChinaEngine())
    }

    @Provides
    @MadeInUSA
    fun provideChinaCar2():ChinaCar
    {
        return ChinaCar(AmericaEngine())
    }
}
```

**使用**

```kotlin
 @Inject
    @MadeInCN
    lateinit var chinaCar1: ChinaCar

    @Inject
    @MadeInUSA
    lateinit var chinaCar2: ChinaCar
```

### 在 Hilt 不支持的类中注入依赖项

EntryPoint

```kotlin
class TestEntryPoint @Inject constructor() {

    @Inject
    lateinit var work: Work

    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface Entries {
        fun getText(): Work
    }


    fun getText(appContext: Context): Work {
        return EntryPoints.get(appContext, Entries::class.java).getText()
    }

}

class Work @Inject constructor() {
    lateinit var workName: String
}

```

注意

> 这里使用了EntryPoints但是还有一个EntryPointAccessors，暂时不知道两者的区别，使用的时候实验