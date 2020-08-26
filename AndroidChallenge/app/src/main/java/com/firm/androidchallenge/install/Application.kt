package com.firm.androidchallenge.install

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.firm.androidchallenge.injection.viewModelModules
import com.firm.androidchallenge.injection.modules
import com.firm.androidchallenge.model.Sound
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    companion object {
        lateinit var favoriteList: MutableList<Sound>
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(modules, viewModelModules))
        }

    }
}