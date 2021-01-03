package com.chengcan.base.view

import android.widget.SeekBar
import com.chengcan.base.api.Callback
import com.chengcan.log.Logger

class SeekBarChangeListenerWrapper(val callback: Callback<Int>) : SeekBar.OnSeekBarChangeListener {

    private val TAG = "SeekBarChangeListenerWrapper"

    private var progress: Int = 0

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            this.progress = progress
        }
        Logger.d(TAG, "progress:" + progress + " fromUser" + fromUser)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        Logger.d(TAG, "onStartTrackingTouch:")

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        Logger.d(TAG, "onStopTrackingTouch:")
        callback.onData(progress)
    }


}