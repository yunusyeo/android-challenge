package com.firm.androidchallenge.injection

import com.firm.androidchallenge.bookSelfFragment.BookselfFragmentViewModel
import com.firm.androidchallenge.categoryDetailFragment.CategoryDetailFragmentViewModel
import com.firm.androidchallenge.firebase.AppDatabase
import com.firm.androidchallenge.myFavoritesFragment.MyFavoritesFragmentViewModel
import com.firm.androidchallenge.repository.Repository

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { AppDatabase()}
    single { Repository(get())}
}

val viewModelModules = module {
    viewModel { MyFavoritesFragmentViewModel(get()) }
    viewModel { BookselfFragmentViewModel(get()) }
    viewModel { CategoryDetailFragmentViewModel(get()) }
}