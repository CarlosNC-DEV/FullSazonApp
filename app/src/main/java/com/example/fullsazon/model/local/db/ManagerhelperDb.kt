package com.example.fullsazon.model.local.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.fullsazon.model.Constantes
import com.example.fullsazon.model.local.CategoriasDb
import com.example.fullsazon.model.local.HistorialDb
import com.example.fullsazon.model.local.HistorialDbConsulta
import com.example.fullsazon.model.local.PedidosDb
import com.example.fullsazon.model.local.PedidosDbConsulta
import com.example.fullsazon.model.local.ProdcutosDb
import com.example.fullsazon.model.local.UsuariosDb
import com.example.fullsazon.model.local.UsuariosDbConsulta

class ManagerhelperDb(context: Context) {
    private  var helperDb: HelperDb
    var db:SQLiteDatabase ?= null

    init {
        helperDb = HelperDb(context)
    }

    fun openBdWr(){
        db = helperDb.writableDatabase
    }

    fun openBdRd(){
        db = helperDb.readableDatabase
    }

    fun closeDb(){
        if(db != null){
            db?.close()
        }
    }

    fun registrarUsuario(usuariosDb: UsuariosDb):Long{
        openBdWr()
        val values = ContentValues()
        values.put(Constantes.NOMBRE_USUARIO, usuariosDb.nombre)
        values.put(Constantes.CIUDAD_USUARIO, usuariosDb.ciudad)
        values.put(Constantes.CORREO_USUARIO, usuariosDb.correo)
        values.put(Constantes.PASSWORD_USUARIO, usuariosDb.password)
        val resul = db?.insert(Constantes.TBL_USUARIOS, null, values)
        return resul!!
    }

    fun iniciarSesion(correo:String, password:String):UsuariosDbConsulta?{
        openBdWr()
        var usuarioVal : UsuariosDbConsulta ?= null
        val query = "${Constantes.QUERY_USUARIOS} WHERE ${Constantes.CORREO_USUARIO}='$correo' AND ${Constantes.PASSWORD_USUARIO}='$password' "
        val resul = db?.rawQuery(query, null)
        if(resul != null && resul.moveToFirst()){
            usuarioVal = UsuariosDbConsulta(
                resul.getInt(0),
                resul.getString(1),
                resul.getString(2),
                resul.getString(3),
                resul.getString(4)
            )
        }
        return usuarioVal
    }

    //=========================

    fun registrarCategoria(categoriasDb: CategoriasDb):Long{
        openBdWr()
        val values = ContentValues()
        values.put(Constantes.ID_CATGEORIA, categoriasDb.id)
        values.put(Constantes.NOMBRE_CATEGORIA, categoriasDb.nombre)
        values.put(Constantes.DESCRIPCION_CATEGORIA, categoriasDb.descripcion)
        values.put(Constantes.IMAGEN_CATEGORIAS, categoriasDb.imagen)
        val resul = db?.insert(Constantes.TBL_CATEGORIAS, null, values)
        return resul!!
    }

    fun categoriasFund():ArrayList<CategoriasDb>{
        openBdRd()
        var categoriasVal : ArrayList<CategoriasDb> = ArrayList()
        val resul = db?.rawQuery(Constantes.QUERY_CATEGORIAS, null)
        if(resul!!.moveToFirst()){
            do {
                val categorias = CategoriasDb(
                    resul.getString(0),
                    resul.getString(1),
                    resul.getString(2),
                    resul.getString(3)
                )
                categoriasVal.add(categorias)
            }while (resul.moveToNext())

        }
        return categoriasVal
    }

    //=========================

    fun registrarProductos(prodcutosDb: ProdcutosDb):Long{
        openBdWr()
        val values = ContentValues()
        values.put(Constantes.ID_PRODUCTO, prodcutosDb.id)
        values.put(Constantes.NOMBRE_PRODUCTO, prodcutosDb.nombre)
        values.put(Constantes.PRECIO_PRODUCTO, prodcutosDb.precio)
        values.put(Constantes.DESCRIPCION_PRODUCTO, prodcutosDb.descripcion)
        values.put(Constantes.IMAGEN_PRODUCTO, prodcutosDb.imagen)
        values.put(Constantes.TIPO_CATEGORIA_PRODUCTO, prodcutosDb.tipo)
        val resul = db?.insert(Constantes.TBL_PRODUCTOS, null, values)
        return resul!!
    }

    fun productosFund(tipo:String):ArrayList<ProdcutosDb>{
        openBdWr()
        var productosVal : ArrayList<ProdcutosDb> = ArrayList()
        val query = "${Constantes.QUERY_PRODUCTOS} WHERE ${Constantes.TIPO_CATEGORIA_PRODUCTO}='$tipo' "
        val resul = db?.rawQuery(query, null)
        if(resul!!.moveToFirst()){
            do {
                val productos = ProdcutosDb(
                    resul.getString(0),
                    resul.getString(1),
                    resul.getString(2),
                    resul.getString(3),
                    resul.getString(4),
                    resul.getString(5),
                )
                productosVal.add(productos)
            }while (resul.moveToNext())
        }
        return productosVal
    }

    fun productoUnique(id:Int):ProdcutosDb?{
        openBdWr()
        var productoVal : ProdcutosDb ?= null
        val query = "${Constantes.QUERY_PRODUCTOS} WHERE ${Constantes.ID_PRODUCTO}='$id'"
        val resul = db?.rawQuery(query, null)
        if(resul != null && resul.moveToFirst()){
            productoVal = ProdcutosDb(
                resul.getString(0),
                resul.getString(1),
                resul.getString(2),
                resul.getString(3),
                resul.getString(4),
                resul.getString(5)
            )
        }
        return productoVal
    }

    //=========================

    fun registrarPedidos(pedidosDb: PedidosDb):Long{
        openBdWr()
        val values = ContentValues()
        values.put(Constantes.ID_PRODUCTO_PEDIDO, pedidosDb.idProductos)
        values.put(Constantes.PRECIO_PEDIDO, pedidosDb.precioAcumulado)
        values.put(Constantes.CANTIDAD_PEDIDO, pedidosDb.cantidad)
        val resul = db?.insert(Constantes.TBL_PEDIDO, null, values)
        return resul!!
    }

    fun pedidosFund():ArrayList<PedidosDbConsulta>{
        openBdRd()
        var pedidosVal : ArrayList<PedidosDbConsulta> = ArrayList()
        val resul = db?.rawQuery(Constantes.QUERY_PEDIDO, null)
        if(resul!!.moveToFirst()){
            do {
                val pedidos = PedidosDbConsulta(
                    resul.getInt(0),
                    resul.getInt(1),
                    resul.getInt(2),
                    resul.getInt(3)
                )
                pedidosVal.add(pedidos)
            }while (resul.moveToNext())
        }
        return pedidosVal
    }

    fun deletePedido(id:Int):Boolean{
        openBdWr()
        val query = "${Constantes.ID_PEDIDO}='$id'"
        val resul = db?.delete(Constantes.TBL_PEDIDO, query, null)
        return resul!! > 0
    }

    fun deleteDataPedido():Boolean{
        openBdWr()
        val resul = db?.delete(Constantes.TBL_PEDIDO, null, null)
        return resul!! > 0
    }

    //=========================

    fun registrarHistorial(historialDb: HistorialDb):Long{
        openBdWr()
        val values = ContentValues()
        values.put(Constantes.FECHA_HISTORIAL, historialDb.fecha)
        values.put(Constantes.USUARIO_HISTORIAL, historialDb.usuario)
        values.put(Constantes.TOTAL_HISTORIAL, historialDb.totalPagp)
        val resul = db?.insert(Constantes.TBL_HISTORIAL, null, values)
        return resul!!
    }

    fun historialFund():ArrayList<HistorialDbConsulta>{
        openBdRd()
        var historialVal : ArrayList<HistorialDbConsulta> = ArrayList()
        val resul = db?.rawQuery(Constantes.QUERY_HISTORIAL, null)
        if(resul!!.moveToFirst()){
            do {
                val historial = HistorialDbConsulta(
                    resul.getInt(0),
                    resul.getString(1),
                    resul.getString(2),
                    resul.getInt(3)
                )
                historialVal.add(historial)
            }while (resul.moveToNext())
        }
        return historialVal
    }



}