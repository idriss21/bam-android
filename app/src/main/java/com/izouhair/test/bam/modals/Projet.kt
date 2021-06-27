package com.izouhair.test.bam.modals

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Projet : RealmObject() {
    @PrimaryKey
    var id = 0
    var node_id: String? = null
    var name: String? = null
    var full_name: String? = null
    var description: String? = null
    var html_url: String? = null
    var homepage: String? = null
    var language: String? = null
    var git_url: String? = null
    var owner: Owner? = null
    var permission: Permission? = null
    var favorite = false     //this field will be used to filter favorites list


}