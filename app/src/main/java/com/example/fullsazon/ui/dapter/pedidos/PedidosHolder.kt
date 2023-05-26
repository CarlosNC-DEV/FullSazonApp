package com.example.fullsazon.ui.dapter.pedidos

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.fullsazon.databinding.ItemsPedidosBinding
import com.example.fullsazon.model.local.PedidosDbConsulta
import com.example.fullsazon.model.local.db.ManagerhelperDb

class PedidosHolder(view: View) : ViewHolder(view) {

    val binding = ItemsPedidosBinding.bind(view)
    private lateinit var managerhelperDb: ManagerhelperDb

    companion object{
        var deleteVal: MutableLiveData<Boolean> = MutableLiveData(false)
        var totalPagoFin : MutableLiveData<Int> = MutableLiveData(0)
    }


    fun render(pedidosDbConsulta: PedidosDbConsulta){
        managerhelperDb = ManagerhelperDb(itemView.context)

        val resul = managerhelperDb.productoUnique(pedidosDbConsulta.idProductos)

        Glide.with(itemView.context)
            .load(resul?.imagen)
            .into(binding.imagenPedido)

        binding.precioUnitario.text = resul?.precio
        binding.precioAcumulado.text = pedidosDbConsulta.precioAcumulado.toString()
        binding.cantidad.text = pedidosDbConsulta.cantidad.toString()

        val agregado = (pedidosDbConsulta.precioAcumulado * 0.19).toInt()
        val precioFinal = pedidosDbConsulta.precioAcumulado + agregado

        totalPagoFin.value = totalPagoFin.value?.plus(precioFinal)

        binding.delete.setOnClickListener{
            val resul = managerhelperDb.deletePedido(pedidosDbConsulta.id)
            if(resul == true){
                deleteVal.value = true
                totalPagoFin.value = totalPagoFin.value?.rem(precioFinal)

            }
        }




    }

}