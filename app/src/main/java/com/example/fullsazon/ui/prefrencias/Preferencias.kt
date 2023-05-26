package com.example.fullsazon.ui.prefrencias

import android.content.Context

class Preferencias(context: Context) {
    val storage = context.getSharedPreferences("FullSazon", 0)

    fun saveRecuerdame(recuerdame:Boolean){
        storage.edit().putBoolean("Recuerdame", recuerdame).apply()
    }

    fun getRecuerdame():Boolean{
        return storage.getBoolean("Recuerdame", false)
    }

    fun saveUsuario(recuerdame:String){
        storage.edit().putString("Usuario", recuerdame).apply()
    }

    fun getUsuario():String?{
        return storage.getString("Usuario", null)
    }

    fun wripe(){
        storage.edit().clear().apply()
    }
}