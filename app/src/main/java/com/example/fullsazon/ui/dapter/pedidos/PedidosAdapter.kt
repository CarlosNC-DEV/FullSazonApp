package com.example.fullsazon.ui.dapter.pedidos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fullsazon.R
import com.example.fullsazon.model.local.PedidosDbConsulta

class PedidosAdapter(var pedidos:List<PedidosDbConsulta>) : RecyclerView.Adapter<PedidosHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PedidosHolder(layoutInflater.inflate(R.layout.items_pedidos, parent, false))
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    override fun onBindViewHolder(holder: PedidosHolder, position: Int) {
        val items = pedidos[position]
        holder.render(items)
    }
}