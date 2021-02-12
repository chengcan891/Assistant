package com.chengcan.bug;

public class NativeCrashHandler {

    static {
        System.loadLibrary("bugly");
    }


    static native void initNativeCrash(String path);

    public static native void testNativeCrash();

}
