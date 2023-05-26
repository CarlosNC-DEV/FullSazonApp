package com.example.fullsazon.model.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fullsazon.model.Constantes

class HelperDb(context: Context) : SQLiteOpenHelper(context, Constantes.DB_NAME, null, Constantes.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constantes.CREATE_TBL_USUARIOS)
        db?.execSQL(Constantes.CREATE_TBL_CATEGORIAS)
        db?.execSQL(Constantes.CREATE_TBL_PRODUCTOS)
        db?.execSQL(Constantes.CREATE_TBL_PEDIDOS)
        db?.execSQL(Constantes.CREATE_TBL_HISTORIAL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}