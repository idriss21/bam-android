package com.izouhair.test.bam.modals

import io.realm.RealmObject

open class Permission : RealmObject() {
    var isAdmin = false
    var isPush = false
    var isPull = false
}