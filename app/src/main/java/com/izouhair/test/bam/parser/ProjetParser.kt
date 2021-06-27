package com.izouhair.test.bam.parser

import com.izouhair.test.bam.modals.Projet
import io.realm.RealmList
import org.json.JSONArray
import org.json.JSONException

object ProjetParser {
    fun getProjets(json: JSONArray): RealmList<Projet> {
        val list = RealmList<Projet>()
        try {
            for (i in 0 until json.length()) {
                val mProjet = Projet()
                val json_owner = json.getJSONObject(i)
                mProjet.id = json_owner.getInt("id")
                mProjet.name = json_owner.getString("name")
                mProjet.full_name = json_owner.getString("full_name")
                mProjet.language = json_owner.getString("language")
                mProjet.html_url = json_owner.getString("html_url")
                mProjet.homepage = json_owner.getString("homepage")
                mProjet.git_url = json_owner.getString("git_url")
                mProjet.node_id = json_owner.getString("node_id")
                mProjet.description = json_owner.getString("description")
                try {
                    mProjet.owner = OwnerParser.getOwner(json_owner.getJSONObject("owner"))
                } catch (e: Exception) {
                    mProjet.owner = null
                }
                try {
                    mProjet.permission =
                        PermissionParser.getPermissions(json_owner.getJSONObject("permission"))
                } catch (e: Exception) {
                    mProjet.permission = null
                }
                list.add(mProjet)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}