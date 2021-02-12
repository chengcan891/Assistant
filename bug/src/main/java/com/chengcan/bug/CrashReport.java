package com.chengcan.bug;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.File;

public class CrashReport {


    public static void init(Context context) {
        Context applicationContext = context.getApplicationContext();

        JavaCrashHandler javaCrashHandler = new JavaCrashHandler(applicationContext);

        File file = new File(applicationContext.getExternalCacheDir(), "native_crash");
        if (!file.exists()) {
            file.mkdirs();
        }
        NativeCrashHandler.initNativeCrash(file.getAbsolutePath());

//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                NativeCrashHandler.testNativeCrash();
//            }
//        },10000);
    }

}
