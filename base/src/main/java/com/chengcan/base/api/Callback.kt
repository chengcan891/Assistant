package com.chengcan.base.api

interface Callback<T> {

    fun onData(data: T)

}