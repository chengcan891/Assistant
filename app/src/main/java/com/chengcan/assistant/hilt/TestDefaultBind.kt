package com.chengcan.assistant.hilt

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class TestDefaultBind @Inject constructor(
    @ActivityContext  val context: Context
) {


}