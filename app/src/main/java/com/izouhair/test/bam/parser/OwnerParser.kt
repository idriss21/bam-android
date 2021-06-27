package com.izouhair.test.bam.parser

import com.izouhair.test.bam.modals.Owner
import org.json.JSONException
import org.json.JSONObject

object OwnerParser {
    fun getOwner(json: JSONObject): Owner {
        val mOwner = Owner()
        try {
            mOwner.id = json.getInt("id")
            mOwner.login = json.getString("login")
            mOwner.url = json.getString("url")
            mOwner.avatar_url = json.getString("avatar_url")
            mOwner.node_id = json.getString("node_id")
            mOwner.isSite_admin = json.getBoolean("site_admin")
            mOwner.type = json.getString("type")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return mOwner
    }
}