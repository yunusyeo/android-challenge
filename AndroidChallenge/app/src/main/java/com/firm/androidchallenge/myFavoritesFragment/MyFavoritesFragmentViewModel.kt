package com.firm.androidchallenge.myFavoritesFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firm.androidchallenge.model.Sound
import com.firm.androidchallenge.repository.Repository

class MyFavoritesFragmentViewModel(val repository: Repository) : ViewModel() {
    private var _data = MutableLiveData<MutableList<Sound>>()
    val data: MutableLiveData<MutableList<Sound>>
        get() = _data

    init {
        fetchData(repository)
    }

    fun fetchData(repository: Repository) {
        _data = repository.getFavoriteListData()
    }

    fun deleteFavoriteData(sound: Sound) {
        repository.deleteFavoriteData(sound.id)
    }
}