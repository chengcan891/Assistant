package com.chengcan.base.utils

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.chengcan.log.Logger
import java.io.File


class MediaPlayerWrapper : LifecycleObserver {

    private val TAG = "MediaPlayerWrapper"

    private val mediaPlayer = MediaPlayer()

    private val handler = Handler(Looper.getMainLooper())

    private var file: File? = null

    private var init = true

    private var exit = false

    private var progressListener: ProgressListener? = null

    fun setFile(file: File) {
        this.file = file
    }

    fun setProgressListener(progressListener: ProgressListener) {
        this.progressListener = progressListener
    }

    fun seekTo(msec: Int) {
        mediaPlayer.seekTo(msec)
    }

    fun startOrPause() {
        file?.let {
            if (mediaPlayer.isPlaying) {
                Logger.d(TAG, "pause")
                mediaPlayer.pause()
            } else {

                if (init) {
                    Logger.d(TAG, "init")

                    onPreInit()

                    init = false
                    mediaPlayer.reset()
                    try {
                        mediaPlayer.setDataSource(it.absolutePath)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                    } catch (e: Exception) {
                    }

                } else {
                    Logger.d(TAG, "start")
                    mediaPlayer.start()
                }

            }
        }
    }

    private fun onPreInit() {
        updateUpdate()
    }

    private fun updateUpdate() {

        handler.postDelayed({
            progressListener?.let {
                if (exit) {
                    return@postDelayed
                }
                it.onPosition(mediaPlayer.duration, mediaPlayer.currentPosition)
                updateUpdate()
            }
        }, 1000)
    }

    fun reset() {

        file?.let {
            if (mediaPlayer.isPlaying) {
                Logger.d(TAG, "seekTo 0")
                mediaPlayer.seekTo(0)
            } else {
                Logger.d(TAG, "reset")
                mediaPlayer.reset()
                try {
                    mediaPlayer.setDataSource(it.absolutePath)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: Exception) {
                }

            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        exit = true
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }

    interface ProgressListener {
        fun onPosition(duration: Int, position: Int)
    }
}