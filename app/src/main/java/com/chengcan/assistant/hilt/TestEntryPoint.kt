package com.chengcan.assistant.hilt

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

class TestEntryPoint @Inject constructor() {

    @Inject
    lateinit var work: Work

    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface Entries {
        fun getText(): Work
    }


    fun getText(appContext: Context): Work {
        return EntryPoints.get(appContext, Entries::class.java).getText()
    }

}

class Work @Inject constructor() {
    lateinit var workName: String
}
