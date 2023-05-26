package com.example.fullsazon.ui.prefrencias

import android.app.Application

class FullSazon : Application() {
    companion object{
        lateinit var preferencias: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        preferencias = Preferencias(applicationContext)
    }
}