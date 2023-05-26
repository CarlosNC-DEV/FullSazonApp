package com.example.fullsazon.model.local

data class PedidosDb (
    var idProductos:Int,
    var precioAcumulado:Int,
    var cantidad:Int
    )

data class PedidosDbConsulta (
    var id:Int,
    var idProductos:Int,
    var precioAcumulado:Int,
    var cantidad:Int
)