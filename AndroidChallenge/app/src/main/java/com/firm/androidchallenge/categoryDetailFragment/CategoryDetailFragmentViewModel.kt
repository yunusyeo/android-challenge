package com.firm.androidchallenge.categoryDetailFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firm.androidchallenge.repository.Repository
import com.firm.androidchallenge.model.Sound

class CategoryDetailFragmentViewModel(val repository: Repository) : ViewModel() {
    private var _data = MutableLiveData<MutableList<Sound>>()
    val data: MutableLiveData<MutableList<Sound>>
        get() = _data

    fun fetchData(categorId:String) {
        _data = repository.getVoiceListData(categorId)
    }

    fun addFavoriteData(sound: Sound) {
        repository.addFavoriteListData(sound)
    }

    fun deleteFavoriteData(sound: Sound) {
        repository.deleteFavoriteData(sound.id)
    }
}