package com.izouhair.test.bam.persistance

import com.izouhair.test.bam.modals.Projet
import io.realm.Realm
import io.realm.RealmList

object ProjetController {
    fun saveProjets(list: RealmList<Projet>): Boolean {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { realm -> realm.copyToRealmOrUpdate(list) }
        return true
    }

    fun favoriteProjets(projet: Projet?, favorite: Boolean): Boolean {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { realm -> // set 1 = favorite
            projet!!.favorite = favorite
            realm.copyToRealmOrUpdate(projet)
        }
        return true
    }

    fun projetsList(isFavorite: Boolean): RealmList<Projet> {
        val realm = Realm.getDefaultInstance()
        val result: MutableList<Projet> =
            realm.where(Projet::class.java).equalTo("favorite", isFavorite).findAll()
                .toMutableList()
        val results = RealmList<Projet>()
        results.addAll(result.subList(0, result.size))
        return results
    }
}