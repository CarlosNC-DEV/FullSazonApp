package com.example.fullsazon.model.local

data class UsuariosDb (
    var nombre:String,
    var ciudad:String,
    var correo:String,
    var password:String
        )

data class UsuariosDbConsulta (
    var id:Int,
    var nombre:String,
    var ciudad:String,
    var correo:String,
    var password:String
)