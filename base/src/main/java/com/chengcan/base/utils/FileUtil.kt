package com.chengcan.base.utils

import com.chengcan.base.api.Callback
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object FileUtil {


    fun read(inputStream: InputStream, callback: Callback<String>) {
        val buffer = BufferedReader(InputStreamReader(inputStream))
        var data: String? = null

        while (true) {
            data = buffer.readLine()
            if (data == null) {
                break
            }
            callback.onData(data)
        }
        buffer.close()
    }

}