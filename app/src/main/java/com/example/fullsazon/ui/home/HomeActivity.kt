package com.example.fullsazon.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.fullsazon.MainActivity
import com.example.fullsazon.R
import com.example.fullsazon.databinding.ActivityHomeBinding
import com.example.fullsazon.ui.dapter.categorias.CategoriasHolder.Companion.remplaceProductos
import com.example.fullsazon.ui.home.fragmentHome.Categorias
import com.example.fullsazon.ui.home.fragmentHome.Historial
import com.example.fullsazon.ui.home.fragmentHome.Pedidos
import com.example.fullsazon.ui.prefrencias.FullSazon.Companion.preferencias

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        remplaceFrgament(Categorias())

        binding.usuario.text = preferencias.getUsuario()

        binding.cerraSesion.setOnClickListener{
            preferencias.wripe()
            startActivity(Intent(this, MainActivity::class.java))
        }

        remplaceProductos.observe(this, Observer { data->
            when(data){
                "DESAYUNOS" -> remplaceFrgament(ProductosFragment())
                "ALMUERZOS" -> remplaceFrgament(ProductosFragment())
                "CENA" -> remplaceFrgament(ProductosFragment())
                "BEBIDAS" -> remplaceFrgament(ProductosFragment())
            }
        })

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.categorias -> remplaceFrgament(Categorias())
                R.id.pedidos -> remplaceFrgament(Pedidos())
                R.id.historial -> remplaceFrgament(Historial())
                else->{
                }
            }
            true
        }



    }

    private fun remplaceFrgament(fragment: Fragment) {
        val fgmManager = supportFragmentManager
        val fragmentTransition = fgmManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout, fragment)
        fragmentTransition.commit()
    }
}