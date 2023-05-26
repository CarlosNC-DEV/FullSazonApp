package com.example.fullsazon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.fullsazon.MainActivity
import com.example.fullsazon.R
import com.example.fullsazon.databinding.ActivitySplashBinding
import com.example.fullsazon.model.local.CategoriasDb
import com.example.fullsazon.model.local.ProdcutosDb
import com.example.fullsazon.model.local.db.ManagerhelperDb
import java.io.BufferedReader
import java.io.InputStreamReader

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var managerhelperDb: ManagerhelperDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerhelperDb = ManagerhelperDb(this)

        cargarCategorias()
        cargarProductos()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },1000)

    }


    private fun cargarCategorias() {
        val archivo = resources.openRawResource(R.raw.categoria)
        val lectura = BufferedReader(InputStreamReader(archivo))

        lectura.useLines { lecturas->
            lecturas.forEach { lectura->
                val data = lectura.split(";").toTypedArray()
                categorias(data)
            }
        }
    }

    private fun categorias(data: Array<String>) {
        val categoriasVal = CategoriasDb(data[0], data[1], data[2], data[3] )
        val resul = managerhelperDb.registrarCategoria(categoriasVal)
        Log.e("TAG", "categorias: $resul", )
    }

    private fun cargarProductos() {
        val archivo = resources.openRawResource(R.raw.productos)
        val lectura = BufferedReader(InputStreamReader(archivo))

        lectura.useLines { lecturas->
            lecturas.forEach { lectura->
                val data = lectura.split(";").toTypedArray()
                productos(data)
            }
        }
    }

    private fun productos(data: Array<String>) {
        val prodcutosVal = ProdcutosDb(data[0], data[1], data[2], data[3], data[4], data[5] )
        val resul = managerhelperDb.registrarProductos(prodcutosVal)
        Log.e("TAG", "produtos: $resul", )
    }

}