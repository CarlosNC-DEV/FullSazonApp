package com.example.fullsazon.ui.dapter.categorias

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.fullsazon.databinding.ItemsCategoriasBinding
import com.example.fullsazon.model.local.CategoriasDb

class CategoriasHolder(view: View) : ViewHolder(view) {

    val binding = ItemsCategoriasBinding.bind(view)
    companion object{
        var remplaceProductos : MutableLiveData<String> = MutableLiveData(null)
    }

    fun render(categoriasDb: CategoriasDb){
        Glide.with(itemView.context)
            .load(categoriasDb.imagen)
            .into(binding.imagen)

        binding.nombre.text = categoriasDb.nombre
        binding.descripcion.text = categoriasDb.descripcion

        itemView.setOnClickListener{
            when(categoriasDb.id){
                "1" -> remplaceProductos.value = "DESAYUNOS"
                "2" -> remplaceProductos.value = "ALMUERZOS"
                "3" -> remplaceProductos.value = "CENA"
                "4" -> remplaceProductos.value = "BEBIDAS"
            }
        }

    }

}