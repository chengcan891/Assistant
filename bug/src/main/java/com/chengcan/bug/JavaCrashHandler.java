package com.chengcan.bug;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public class JavaCrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private Context context;

    public JavaCrashHandler(Context applicationContext) {
        context = applicationContext;
        defaultUncaughtExceptionHandler =
                Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        File dir = new File(context.getExternalCacheDir(), "crash_info");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long l = System.currentTimeMillis();
        File file = new File(dir, l + ".txt");

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println("time: xxx");
            pw.println("thread: " + t.getName());
            e.printStackTrace(pw);
            pw.flush();
            pw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(t, e);
            }
        }
    }
}
