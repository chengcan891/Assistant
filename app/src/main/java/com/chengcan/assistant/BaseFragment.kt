package com.chengcan.assistant

import android.util.Log
import androidx.fragment.app.Fragment

abstract class BaseFragment:Fragment(), KeyAction {


    override fun onBackPressed():Boolean {
        Log.i("Fragment", this.toString())
        return false
    }
}