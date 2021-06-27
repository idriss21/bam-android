package com.izouhair.test.bam.network

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class SimpleRequest(
    method: Int,
    url: String?,
    listener: Response.Listener<String?>?,
    errorListener: Response.ErrorListener?
) : StringRequest(method, url, listener, errorListener) {
    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        val customHeader: MutableMap<String, String> = HashMap()
        customHeader["Content-Type"] = "application/json"
        Log.e("getHeaders", customHeader.toString())
        return customHeader
    }

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String>? {
        return super.getParams()
    }

    companion object {
        var TIME_OUT = 40000
    }
}