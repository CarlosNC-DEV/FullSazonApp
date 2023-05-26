package com.example.fullsazon.ui.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fullsazon.R
import com.example.fullsazon.databinding.ActivityRegistroBinding
import com.example.fullsazon.model.local.UsuariosDb
import com.example.fullsazon.model.local.db.ManagerhelperDb
import com.example.fullsazon.ui.prefrencias.FullSazon.Companion.preferencias
import com.example.fullsazon.ui.recomendado.RecomendadoActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var managerhelperDb: ManagerhelperDb
    private lateinit var builder : AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerhelperDb = ManagerhelperDb(this)
        builder = AlertDialog.Builder(this)

        binding.btnRegistro.setOnClickListener {
            validarCredenciales()
        }
    }

    private fun validarCredenciales() {
        val nombre = binding.nombre.text.toString()
        val ciudad = binding.ciudad.text.toString()
        val correo = binding.correo.text.toString()
        val password = binding.password.text.toString()
        if(nombre.isNotEmpty() && ciudad.isNotEmpty() && correo.isNotEmpty() && password.isNotEmpty()){
            builder.setTitle("POLITICAS")
                .setMessage("El Restaurante, trabajará continuamente en la producción y elaboración de alimentos de la mas alta calidad cumplimiento con las especificaciones del mercado local, regional, nacional e internacional para la completa satisfacción de los clientes y consumidores, promoviendo la implementación de sistemas que garanticen la total inocuidad de los productos con la incorporación de procesos seguros para el bienestar y beneficio de sus clientes, trabajadores, socios y proveedores además de la protección del medios ambiente.")
                .setNegativeButton("Rechazar"){act, it->
                    Toast.makeText(this, "No fue posible crear ti cuenta", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Aceptar"){act, it->
                    val usuarioVal = UsuariosDb(nombre, ciudad, correo, password)
                    val resul = managerhelperDb.registrarUsuario(usuarioVal)
                    if(resul > 0){
                        preferencias.saveUsuario(nombre)
                        preferencias.saveRecuerdame(true)
                        startActivity(Intent(this, RecomendadoActivity::class.java))
                    }else{
                        Toast.makeText(this, "Error al crear tu cuenta", Toast.LENGTH_SHORT).show()
                    }
                }
                .show()
        }else{
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show()
        }
    }
}