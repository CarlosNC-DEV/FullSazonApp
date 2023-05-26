package com.example.fullsazon.ui.dapter.historial

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fullsazon.databinding.ItemsHistorialBinding
import com.example.fullsazon.model.local.HistorialDbConsulta

class HistorialHolder(view: View) : ViewHolder(view) {

    val binding = ItemsHistorialBinding.bind(view)

    fun render(historialDbConsulta: HistorialDbConsulta){
        binding.numHitsorial.text = historialDbConsulta.id.toString()
        binding.fecha.text = historialDbConsulta.fecha
        binding.usuario.text = historialDbConsulta.usuario
        binding.precio.text = historialDbConsulta.totalPagp.toString()
    }

}