package com.example.fullsazon.ui.dapter.productos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fullsazon.R
import com.example.fullsazon.model.local.ProdcutosDb

class ProdcutosAdapter(var productos:List<ProdcutosDb>) : RecyclerView.Adapter<ProductosHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductosHolder(layoutInflater.inflate(R.layout.items_productos, parent, false))
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ProductosHolder, position: Int) {
        val items = productos[position]
        holder.render(items)
    }
}