package com.izouhair.test.bam.parser

import com.izouhair.test.bam.modals.Permission
import org.json.JSONException
import org.json.JSONObject

object PermissionParser {
    fun getPermissions(json: JSONObject): Permission {
        val mPermission = Permission()
        try {
            mPermission.isAdmin = json.getBoolean("admin")
            mPermission.isPull = json.getBoolean("pull")
            mPermission.isPush = json.getBoolean("push")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return mPermission
    }
}