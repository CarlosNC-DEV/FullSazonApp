package com.example.fullsazon.ui.recomendado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.fullsazon.MainActivity
import com.example.fullsazon.R
import com.example.fullsazon.databinding.ActivityRecomendadoBinding
import com.example.fullsazon.ui.home.HomeActivity
import com.example.fullsazon.ui.prefrencias.FullSazon.Companion.preferencias

class RecomendadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecomendadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("https://cdn.colombia.com/gastronomia/2011/08/02/bandeja-paisa-1616.gif")
            .into(binding.imageView4)

        binding.nombreRecomendando.text = "BANDEJA PAISA"
        binding.descripcionRecomendaodo.text = "Con frijol, Arroz, Chicharron, Chorizo, Huevo frito, Arepa, Rellena, Aguacate y tajadas"
        binding.precioRecomendado.text = "25000"


        binding.atras.setOnClickListener {
            preferencias.wripe()
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.siguinete.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }


    }
}