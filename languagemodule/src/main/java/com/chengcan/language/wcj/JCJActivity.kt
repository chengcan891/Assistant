package com.chengcan.language.wcj

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chengcan.base.adapter.CommonRecyclerViewAdapter
import com.chengcan.base.api.Callback
import com.chengcan.base.utils.*
import com.chengcan.base.view.SeekBarChangeListenerWrapper
import com.chengcan.language.R
import com.chengcan.log.Logger
import kotlinx.android.synthetic.main.languagemodule_activity_jcj.*
import java.io.File
import java.io.FileInputStream

class JCJActivity : AppCompatActivity() {

    val TAG = "JCJActivity"

    private val mediaPlayer = MediaPlayerWrapper()

    private val dir = File(Environment.getExternalStorageDirectory(), "JCJ/C3/37")
    val imgDir = File(dir, "img")
    private val voice = "VOICE.mp3"
    private val textConfig = "TEXT.txt"
    private lateinit var adapter: CommonRecyclerViewAdapter<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.languagemodule_activity_jcj)
        lifecycle.addObserver(mediaPlayer)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mediaPlayer)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initEvent()
        initView()
        updateView()
    }

    val configs = mutableListOf<Item>()

    private fun updateView() {
        PublicHandler.runOnWorkThread(Runnable {

            val voice = File(dir, voice)
            mediaPlayer.setFile(voice)
            val text = File(dir, textConfig)

            //            val input = resources.openRawResource(R.raw.languagemodule_37)
            val input = FileInputStream(text)



            FileUtil.read(input, object : Callback<String> {
                override fun onData(data: String) {
                    val d = data.split(",")
                    if (d.size == 4 && NumberUtil.parseInt(d[0], 0) == 2) {
                        configs.add(Item(TimeUtil.parseTime(d[1]), d[3], d[2]))
                    }
                }
            })

            input.close()

            PublicHandler.runOnUIThread(Runnable {
                adapter.update(configs)
            })
        })
    }

    private fun initView() {
        val list = mutableListOf<Item>()
        adapter = object : CommonRecyclerViewAdapter<Item>(
            this,
            list,
            android.R.layout.simple_list_item_1,
            null
        ) {
            override fun convert(holder: VH, item: Item) {
                val context = holder.getView<TextView>(android.R.id.text1)
                context.text = item.context
                context.setOnClickListener {
                    mediaPlayer.seekTo(item.time.toInt())
                    displayImage(item)
                }

            }
        }
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        recyclerview.adapter = adapter
    }

    private var lastImage: String? = null

    private  fun displayImage(item: Item) {

        if (item.img == lastImage) {
            return
        }

        val imageView = File(imgDir, item.img)
        if (imageView.exists()) {
            lastImage = item.img
            val bitmap = BitmapFactory.decodeFile(imageView.absolutePath)
            image.setImageBitmap(bitmap)
            zoomImageView.setImageBitmap(bitmap)
        }
    }

    private fun initEvent() {
        start.setOnClickListener {
            mediaPlayer.startOrPause()
        }

        image.setOnClickListener {
            zoomImageView.visibility = View.VISIBLE
        }

        zoomImageView.setOnClickListener {
            zoomImageView.visibility = View.GONE
        }

        seekBar.setOnSeekBarChangeListener(SeekBarChangeListenerWrapper(object :Callback<Int>{
            override fun onData(data: Int) {
                mediaPlayer.seekTo(data)
            }
        }))

        mediaPlayer.setProgressListener(object : MediaPlayerWrapper.ProgressListener {
            override fun onPosition(duration: Int,position: Int) {

                seekBar.max = duration
                seekBar.progress = position

                if (position == 0) {
                    displayImage(configs[0])
                    return
                }

                var item: Item? = null
                for (index in configs.indices) {
                    if (configs[index].time < position) {
                        continue
                    }

                    item = if (index == 0) {
                        configs[index]
                    } else {
                        configs[index - 1]
                    }
                    break
                }

                item?.let {
                    Logger.d(TAG, "time: " + it.time + " position:" + position)
                    displayImage(it)
                }
            }
        })
    }


    data class Item(val time: Long, val context: String, val img: String)
}