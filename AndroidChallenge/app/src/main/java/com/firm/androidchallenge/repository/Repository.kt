package com.firm.androidchallenge.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.firm.androidchallenge.firebase.AppDatabase
import com.firm.androidchallenge.model.Category
import com.firm.androidchallenge.model.Sound
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class Repository(val appDatabase: AppDatabase) {

    var favoriteList: MutableLiveData<MutableList<Sound>> = MutableLiveData()
    var soundList: MutableLiveData<MutableList<Sound>> = MutableLiveData()
    var categoryList: MutableLiveData<MutableList<Category>> = MutableLiveData()

    fun addFavoriteListData(sound: Sound) {
        val id: String = appDatabase.getDatabaseRefences().child("favoriteList").push().getKey()!!
        sound.id = id
        appDatabase.getDatabaseRefences().child("favoriteList").child(id).setValue(sound)
    }

    fun getFavoriteListData(): MutableLiveData<MutableList<Sound>> {
        appDatabase.getDatabaseRefences().child("favoriteList")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var list: MutableList<Sound> = ArrayList()

                    dataSnapshot.children.forEach {
                        val sound = it.getValue(Sound::class.java)
                        list.add(sound!!)
                    }
                    favoriteList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Error", error.toString())
                }
            })
        return favoriteList
    }

    fun getCategoryListData(): MutableLiveData<MutableList<Category>> {
        appDatabase.getDatabaseRefences().child("categoryList")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var list: MutableList<Category> = ArrayList()

                    dataSnapshot.children.forEach {
                        val category = it.getValue(Category::class.java)
                        list.add(category!!)
                    }
                    categoryList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Error", error.toString())
                }
            })
        return categoryList
    }

    fun getVoiceListData(categorId: String): MutableLiveData<MutableList<Sound>> {
        appDatabase.getDatabaseRefences().child("soundList")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var list: MutableList<Sound> = ArrayList()
                    //categoryId ye gre listeleme yapılıyor
                    dataSnapshot.children.forEach {
                        var sound = it.getValue(Sound::class.java)
                        if (sound?.categoryId.equals(categorId))
                            sound?.let { it1 -> list.add(it1) }
                    }
                    soundList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Error", error.toString())
                }
            })

        return soundList

    }

    fun deleteFavoriteData(id: String) {
        appDatabase.getDatabaseRefences().child("favoriteList").child(id).removeValue()
    }


}