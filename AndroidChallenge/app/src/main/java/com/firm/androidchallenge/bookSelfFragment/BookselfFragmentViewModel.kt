package com.firm.androidchallenge.bookSelfFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firm.androidchallenge.model.Category
import com.firm.androidchallenge.repository.Repository

class BookselfFragmentViewModel(repository: Repository) : ViewModel() {
    private var _data = MutableLiveData<MutableList<Category>>()
    val data: MutableLiveData<MutableList<Category>>
        get() = _data

    init {
        fetchData(repository)
    }

    fun fetchData(repository: Repository) {
        _data = repository.getCategoryListData()
    }
}