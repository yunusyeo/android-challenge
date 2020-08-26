package com.firm.androidchallenge.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AppDatabase {
    fun getDatabaseRefences(): DatabaseReference = FirebaseDatabase.getInstance().reference

}