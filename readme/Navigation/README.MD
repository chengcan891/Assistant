



https://developer.android.google.cn/guide/navigation/navigation-conditional



https://developer.android.google.cn/guide/navigation/navigation-getting-started

# Navigation 使用入门

**1.环境要求**

- Android Studio 3.2 or higher
- Emulator or device running API 14+

**2.新建项目**

使用Kotlin编写

**3.设置您的环境**

要在您的项目中添加 Navigation 支持，请向应用的 `build.gradle` 文件添加以下依赖项：

```groovy
dependencies {
  def nav_version = "2.3.0"

  // Java language implementation
  implementation "androidx.navigation:navigation-fragment:$nav_version"
  implementation "androidx.navigation:navigation-ui:$nav_version"

  // Kotlin
  implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
  implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

  // Feature module Support
  implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"

  // Testing Navigation
  androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"
}
```

**4.创建导航图**

要向项目添加导航图，请执行以下操作：

1. 在“Project”窗口中，右键点击 `res` 目录，然后依次选择 **New > Android Resource File**。此时系统会显示 **New Resource File** 对话框。
2. 在 **File name** 字段中输入名称，例如“nav_graph”。
3. 从 **Resource type** 下拉列表中选择 Navigation，然后点击 OK。

当您添加首个导航图时，Android Studio 会在 `res` 目录内创建一个 `navigation` 资源目录。该目录包含您的导航图资源文件（例如 `nav_graph.xml`）。

**5.了解Navigation Editor**

添加图表后，Android Studio 会在 Navigation Editor 中打开该图表。在 Navigation Editor 中，您可以直观地修改导航图，或直接修改底层 XML。

1. **Destinations panel**：列出了导航宿主和目前位于 Graph Editor 中的所有目的地。
2. **Graph Editor**：包含导航图的视觉表示形式。您可以在 **Design** 视图和 **Text** 视图中的底层 XML 表示形式之间切换。
3. **Attributes**：显示导航图中当前所选项的属性。

**6.向 Activity 添加 NavHost**

导航宿主是 Navigation 组件的核心部分之一。导航宿主是一个空容器，用户在您的应用中导航时，目的地会在该容器中交换进出。

以下 XML 示例显示了作为应用主 Activity 一部分的 `NavHostFragment`：

```xml
    <?xml version="1.0" encoding="utf-8"?>
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <androidx.appcompat.widget.Toolbar
            .../>

        <fragment
            android:id="@+id/nav_host_fragment"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"

            app:defaultNavHost="true"
            app:navGraph="@navigation/nav_graph" />

        <com.google.android.material.bottomnavigation.BottomNavigationView
            .../>

    </android.support.constraint.ConstraintLayout>
    
```

这里Toolbar和BottomNavigationView不是必须的

请注意以下几点：

- `android:name` 属性包含 `NavHost` 实现的类名称。
- `app:navGraph` 属性将 `NavHostFragment` 与导航图相关联。导航图会在此 `NavHostFragment` 中指定用户可以导航到的所有目的地。
- `app:defaultNavHost="true"` 属性确保您的 `NavHostFragment` 会拦截系统返回按钮。请注意，只能有一个默认 `NavHost`。如果同一布局（例如，双窗格布局）中有多个主机，请务必仅指定一个默认 `NavHost`。

您也可以使用 [Layout Editor](https://developer.android.google.cn/studio/write/layout-editor) 向 Activity 添加 `NavHostFragment`，具体操作步骤如下：

1. 在项目文件列表中，双击 Activity 的布局 XML 文件，以在 Layout Editor 中将其打开。
2. 在 **Palette** 窗格内，选择 Containers 类别，或者搜索“NavHostFragment”。
3. 将 `NavHostFragment` 视图拖动到您的 Activity 上。
4. 接下来，在随即显示的 **Navigation Graphs** 对话框中，选择要与此 `NavHostFragment` 相关联的相应导航图，然后点击 OK

**7.向导航图添加目的地**

您可以从现有的 Fragment 或 Activity 创建目的地。您还可以使用 Navigation Editor 创建新目的地，或创建占位符以便稍后替换为 Fragment 或 Activity。

在本示例中，我们来创建一个新目的地。要使用 Navigation Editor 添加新目的地，请执行以下操作：

1. 在 Navigation Editor 中，点击 **New Destination** 图标 ![img](https://developer.android.google.cn/images/topic/libraries/architecture/navigation-new-destination-icon.png)，然后点击 Create new destination。
2. 在随即显示的 **New Android Component** 对话框中，创建您的 Fragment。如需详细了解 Fragment，请参阅 [Fragment 文档](https://developer.android.google.cn/guide/components/fragments)。

当您返回到 Navigation Editor 中时，会发现 Android Studio 已将此目的地添加到图表中。



**8.目的地详解**

点击一个目的地以将其选中，并注意 **Attributes** 面板中显示的以下属性：

- **Type** 字段指示在您的源代码中，该目的地是作为 Fragment、Activity 还是其他自定义类实现的。
- **Label** 字段包含该目的地的 XML 布局文件的名称。
- **ID** 字段包含该目的地的 ID，它用于在代码中引用该目的地。
- **Class** 下拉列表显示与该目的地相关联的类的名称。您可以点击此下拉列表，将相关联的类更改为其他目的地类型。

点击 **Text** 标签页可查看导航图的 XML 视图。XML 中同样包含该目的地的 `id`、`name`、`label` 和 `layout` 属性，如下所示：

```xml
    <?xml version="1.0" encoding="utf-8"?>
    <navigation xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:android="http://schemas.android.com/apk/res/android"
        app:startDestination="@id/blankFragment">
        <fragment
            android:id="@+id/blankFragment"
            android:name="com.example.cashdog.cashdog.BlankFragment"
            android:label="Blank"
            tools:layout="@layout/fragment_blank" />
    </navigation>
    
```



**9.连接目的地**

您可以使用 Navigation Editor 将两个目的地连接起来，具体操作步骤如下：

1. 在 **Design** 标签页中，将鼠标悬停在您希望用户从中导航出来的目的地的右侧。该目的地右侧上方会显示一个圆圈，如图 4 所示。

   ![img](https://developer.android.google.cn/images/topic/libraries/architecture/navigation-actioncircle_2x.png)**图 4.** 一个包含操作连接圆圈的目的地

2. 点击您希望用户导航到的目的地，并将光标拖动到该目的地的上方，然后松开。这两个目的地之间生成的线条表示操作，如图 5 所示。

   ![img](https://developer.android.google.cn/images/topic/libraries/architecture/navigation-connected_2x.png)**图 5.** 通过操作连接目的地

3. 点击箭头以突出显示该操作。此时 **Attributes** 面板中会显示以下属性：

   - **Type** 字段包含“Action”。
   - **ID** 字段包含该操作的 ID。
   - **Destination** 字段包含目的地 Fragment 或 Activity 的 ID。

4. 点击 **Text** 标签页，以切换到 XML 视图。现在，一个 action 元素已添加到源目的地中。该操作有一个 ID 和一个目的地属性（其中包含下一个目的地的 ID），如以下示例所示：

```xml
<?xml version="1.0" encoding="utf-8"?>
    <navigation xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:android="http://schemas.android.com/apk/res/android"
        app:startDestination="@id/blankFragment">
        <fragment
            android:id="@+id/blankFragment"
            android:name="com.example.cashdog.cashdog.BlankFragment"
            android:label="fragment_blank"
            tools:layout="@layout/fragment_blank" >
            <action
                android:id="@+id/action_blankFragment_to_blankFragment2"
                app:destination="@id/blankFragment2" />
        </fragment>
        <fragment
            android:id="@+id/blankFragment2"
            android:name="com.example.cashdog.cashdog.BlankFragment2"
            android:label="fragment_blank_fragment2"
            tools:layout="@layout/fragment_blank_fragment2" />
    </navigation>
    
```

**10.导航到目的地**

航到目的地是使用 [`NavController`](https://developer.android.google.cn/reference/androidx/navigation/NavController) 完成的，后者是一个在 `NavHost` 中管理应用导航的对象。每个 `NavHost` 均有自己的相应 `NavController`。您可以使用以下方法之一检索 `NavController`：

**Kotlin**：

- [`Fragment.findNavController()`](https://developer.android.google.cn/reference/kotlin/androidx/navigation/fragment/package-summary#findnavcontroller)
- [`View.findNavController()`](https://developer.android.google.cn/reference/kotlin/androidx/navigation/package-summary#(android.view.View).findNavController())
- [`Activity.findNavController(viewId: Int)`](https://developer.android.google.cn/reference/kotlin/androidx/navigation/package-summary#findnavcontroller)



第一种方式：使用目的地

```kotlin
//定义动画
val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

//blankFragment2目的地
text.setOnClickListener {
        findNavController(this).navigate(R.id.blankFragment2,null, options)
}
```

第二种方式：使用操作

```kotlin
 // action_blankFragment_to_blankFragment2是一个操作
text.setOnClickListener( Navigation.createNavigateOnClickListener(R.id.action_blankFragment_to_blankFragment2, null))

```



第三种方式：使用 Safe Args 确保类型安全

要将 [Safe Args](https://developer.android.google.cn/topic/libraries/architecture/navigation/navigation-pass-data#Safe-args) 添加到您的项目，请在顶级 `build.gradle` 文件中包含以下 `classpath`：

```groovy
buildscript {
        repositories {
            google()
        }
        dependencies {
            def nav_version = "2.3.0-alpha01"
            classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
        }
    }
```

要生成适用于 Java 或 Java 和 Kotlin 混合模块的 Java 语言代码，请将以下行添加到**应用或模块**的 `build.gradle` 文件中：

```kotlin
apply plugin: "androidx.navigation.safeargs"
```



blankFragment2添加参数argument1

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/blankFragment">
    <fragment
        android:id="@+id/blankFragment"
        android:name="com.chengcan.testnavication.BlankFragment"
        android:label="fragment_blank"
        tools:layout="@layout/fragment_blank" >
        <action
            android:id="@+id/action_blankFragment_to_blankFragment2"
            app:destination="@id/blankFragment2"
            app:enterAnim="@anim/slide_in_right"
            app:exitAnim="@anim/slide_out_left"
            app:popEnterAnim="@anim/slide_in_left"
            app:popExitAnim="@anim/slide_out_right" />

    </fragment>
    <fragment
        android:id="@+id/blankFragment2"
        android:name="com.chengcan.testnavication.BlankFragment2"
        android:label="fragment_blank2"
        tools:layout="@layout/fragment_blank2" >
        <argument
            android:name="argument1"
            app:argType="integer"
            android:defaultValue="2" />
    </fragment>
</navigation>
```



BlankFragment1启动跳转

```kotlin
text.setOnClickListener {
            val argument1 = 1
            val action = BlankFragmentDirections.actionBlankFragmentToBlankFragment2(argument1)
            findNavController(this).navigate(action)
        }
```



BlankFragment2获取参数

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val safeArgs: BlankFragment2Args by navArgs()
        var argument1 = safeArgs.argument1
}
```







