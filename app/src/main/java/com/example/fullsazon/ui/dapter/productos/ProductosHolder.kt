package com.example.fullsazon.ui.dapter.productos

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.fullsazon.R
import com.example.fullsazon.databinding.ItemsProductosBinding
import com.example.fullsazon.model.local.PedidosDb
import com.example.fullsazon.model.local.ProdcutosDb
import com.example.fullsazon.model.local.db.ManagerhelperDb

class ProductosHolder(view: View) : ViewHolder(view) {

    val binding = ItemsProductosBinding.bind(view)
    private lateinit var managerhelperDb: ManagerhelperDb
    private lateinit var builer : AlertDialog.Builder

    var cantidadCount: Int = 0
    var precioAcumulado:Int = 0

    fun render(prodcutosDb: ProdcutosDb){

        managerhelperDb = ManagerhelperDb(itemView.context)
        builer = AlertDialog.Builder(itemView.context)

        Glide.with(itemView.context)
            .load(prodcutosDb.imagen)
            .into(binding.imagenProcutos)

        binding.nombre.text = prodcutosDb.nombre
        binding.descripcion.text = prodcutosDb.descripcion
        binding.precio.text = prodcutosDb.precio

        itemView.setOnClickListener{
            builer.setTitle(prodcutosDb.nombre)

            val lyo = LayoutInflater.from(itemView.context)
            val layout = lyo.inflate(R.layout.dialog_productos, null)

            val imagen = layout.findViewById<ImageView>(R.id.imagenPr)

            val descripcion = layout.findViewById<TextView>(R.id.descripcion)
            val precio = layout.findViewById<TextView>(R.id.precio)

            val cantidad = layout.findViewById<TextView>(R.id.cantidad)

            val menos = layout.findViewById<Button>(R.id.menos)
            val mas = layout.findViewById<Button>(R.id.mas)

            Glide.with(itemView.context)
                .load(prodcutosDb.imagen)
                .into(imagen)
            descripcion.text = prodcutosDb.descripcion
            precio.text = prodcutosDb.precio

            menos.setOnClickListener{
                if(cantidadCount > 0){
                    cantidadCount--
                    cantidad.text = cantidadCount.toString()
                    precioAcumulado = prodcutosDb.precio.toInt() * cantidadCount
                }
            }

            mas.setOnClickListener{
                cantidadCount++
                cantidad.text = cantidadCount.toString()
                precioAcumulado = prodcutosDb.precio.toInt() * cantidadCount
            }




            builer.setView(layout)
            builer.setNegativeButton("Atras"){act, it->

            }
            builer.setPositiveButton("Agreagar a pedido"){act, it ->
                if(cantidadCount != 0){
                    val pedidosVal = PedidosDb(prodcutosDb.id.toInt(), precioAcumulado, cantidadCount)
                    val resul = managerhelperDb.registrarPedidos(pedidosVal)
                    if(resul > 0){
                        Toast.makeText(itemView.context, "Agregado al pedido", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(itemView.context, "Error al agregar al pedido", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(itemView.context, "Cantidad minima 1 producto", Toast.LENGTH_SHORT).show()
                }
            }
            builer.show()


        }

    }

}