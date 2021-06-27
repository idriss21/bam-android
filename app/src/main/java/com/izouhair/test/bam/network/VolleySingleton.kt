package com.izouhair.test.bam.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context: Context?) {
    val requestQueue: RequestQueue

    companion object {
        private var sInstance: VolleySingleton? = null
        @Synchronized
        fun getInstance(context: Context?): VolleySingleton? {
            if (sInstance == null) {
                sInstance = VolleySingleton(context)
            }
            return sInstance
        }
    }

    init {
        requestQueue = Volley.newRequestQueue(context)
    }
}