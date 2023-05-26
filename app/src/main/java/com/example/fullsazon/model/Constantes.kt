package com.example.fullsazon.model

class Constantes {
    companion object {
        val DB_NAME: String = "FullSazon"
        val DB_VERSION: Int = 1

        val TBL_USUARIOS: String = "Usuarios"
        val ID_USUARIO: String = "id"
        val NOMBRE_USUARIO: String = "nombre"
        val CIUDAD_USUARIO: String = "ciudad"
        val CORREO_USUARIO: String = "correo"
        val PASSWORD_USUARIO: String = "password"


        val CREATE_TBL_USUARIOS: String = "CREATE TABLE $TBL_USUARIOS ( " +
                "$ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NOMBRE_USUARIO TEXT, " +
                "$CIUDAD_USUARIO TEXT, " +
                "$CORREO_USUARIO TEXT, " +
                "$PASSWORD_USUARIO TEXT)"

        val QUERY_USUARIOS: String = "SELECT * FROM $TBL_USUARIOS "

        //===============================================================

        val TBL_CATEGORIAS: String = "Categorias"
        val ID_CATGEORIA: String = "id"
        val NOMBRE_CATEGORIA: String = "nombre"
        val DESCRIPCION_CATEGORIA: String = "descripcion"
        val IMAGEN_CATEGORIAS : String = "imagen"


        val CREATE_TBL_CATEGORIAS : String = "CREATE TABLE $TBL_CATEGORIAS ( " +
                "$ID_CATGEORIA INTEGER PRIMARY KEY UNIQUE, " +
                "$NOMBRE_CATEGORIA TEXT, " +
                "$DESCRIPCION_CATEGORIA TEXT, " +
                "$IMAGEN_CATEGORIAS TEXT)"

        val QUERY_CATEGORIAS: String = "SELECT * FROM $TBL_CATEGORIAS "

        //===============================================================

        val TBL_PRODUCTOS: String = "Productos"
        val ID_PRODUCTO: String = "id"
        val NOMBRE_PRODUCTO: String = "nombre"
        val PRECIO_PRODUCTO: String = "precio"
        val DESCRIPCION_PRODUCTO : String = "descripcion"
        val IMAGEN_PRODUCTO : String = "imagen"
        val TIPO_CATEGORIA_PRODUCTO : String = "tipoCategoria"


        val CREATE_TBL_PRODUCTOS : String = "CREATE TABLE $TBL_PRODUCTOS ( " +
                "$ID_PRODUCTO INTEGER PRIMARY KEY UNIQUE, " +
                "$NOMBRE_PRODUCTO TEXT, " +
                "$PRECIO_PRODUCTO INTEGER, " +
                "$DESCRIPCION_PRODUCTO TEXT, " +
                "$IMAGEN_PRODUCTO TEXT, " +
                "$TIPO_CATEGORIA_PRODUCTO TEXT)"

        val QUERY_PRODUCTOS: String = "SELECT * FROM $TBL_PRODUCTOS "

        //===============================================================

        val TBL_PEDIDO: String = "Pedido"
        val ID_PEDIDO: String = "id"
        val ID_PRODUCTO_PEDIDO: String = "idProducto"
        val PRECIO_PEDIDO: String = "precio"
        val CANTIDAD_PEDIDO : String = "cantidad"


        val CREATE_TBL_PEDIDOS : String = "CREATE TABLE $TBL_PEDIDO ( " +
                "$ID_PEDIDO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$ID_PRODUCTO_PEDIDO INTEGER, " +
                "$PRECIO_PEDIDO INTEGER, " +
                "$CANTIDAD_PEDIDO INTEGER)"

        val QUERY_PEDIDO: String = "SELECT * FROM $TBL_PEDIDO "

        //===============================================================

        val TBL_HISTORIAL: String = "Historial"
        val ID_HISTORIAL: String = "id"
        val FECHA_HISTORIAL: String = "fecha"
        val USUARIO_HISTORIAL: String = "usuario"
        val TOTAL_HISTORIAL : String = "total"


        val CREATE_TBL_HISTORIAL : String = "CREATE TABLE $TBL_HISTORIAL ( " +
                "$ID_HISTORIAL INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$FECHA_HISTORIAL TEXT, " +
                "$USUARIO_HISTORIAL TEXT, " +
                "$TOTAL_HISTORIAL INTEGER)"

        val QUERY_HISTORIAL: String = "SELECT * FROM $TBL_HISTORIAL "




    }
}