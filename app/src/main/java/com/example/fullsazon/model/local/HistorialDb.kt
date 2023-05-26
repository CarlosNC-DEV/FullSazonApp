package com.example.fullsazon.model.local

data class HistorialDb (
    var fecha:String,
    var usuario:String,
    var totalPagp:Int
    )

data class HistorialDbConsulta (
    var id:Int,
    var fecha:String,
    var usuario:String,
    var totalPagp:Int
)