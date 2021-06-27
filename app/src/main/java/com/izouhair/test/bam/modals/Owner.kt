package com.izouhair.test.bam.modals

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Owner : RealmObject() {
    @PrimaryKey
    var id = 0
    var login: String? = null
    var node_id: String? = null
    var avatar_url: String? = null
    var url: String? = null
    var type: String? = null
    var isSite_admin = false
}