package com.example.fullsazon.ui.dapter.categorias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fullsazon.R
import com.example.fullsazon.model.local.CategoriasDb

class CategoriasAdapter(var categorias:List<CategoriasDb>) : RecyclerView.Adapter<CategoriasHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriasHolder(layoutInflater.inflate(R.layout.items_categorias, parent, false))
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    override fun onBindViewHolder(holder: CategoriasHolder, position: Int) {
        val items = categorias[position]
        holder.render(items)
    }
}