**1.系统记录的文件**
- 有root权限，直接去访问  
/data/anr/
- adb bugreport anrlog.zip
会导出文件到/bugreport

> 代码是没有权限访问文件

**2.代码拦截**  
ANRWatchDog+

