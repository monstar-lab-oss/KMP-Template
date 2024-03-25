package com.monstarlab.kmp

import android.app.Application
import android.content.Context
import di.initKoin
import org.koin.dsl.module

class AndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(appModule = module { single<Context> { this@AndroidApp } })
    }
}
