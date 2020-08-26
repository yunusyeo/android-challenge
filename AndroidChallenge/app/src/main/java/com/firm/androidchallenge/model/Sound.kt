package com.firm.androidchallenge.model

import com.google.firebase.database.Exclude

data class Sound(
    var id: String = "",
    var categoryId: String = "",
    var soundUrl: String = "",
    @Exclude
    var isFavorite: Boolean = false,
    var title: String = ""
)

data class Category(
    var categoryId: String = "",
    var categoryName: String = ""
)

