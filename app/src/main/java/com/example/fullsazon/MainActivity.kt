package com.example.fullsazon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fullsazon.databinding.ActivityMainBinding
import com.example.fullsazon.model.local.db.ManagerhelperDb
import com.example.fullsazon.ui.prefrencias.FullSazon.Companion.preferencias
import com.example.fullsazon.ui.recomendado.RecomendadoActivity
import com.example.fullsazon.ui.registro.RegistroActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var managerhelperDb: ManagerhelperDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerhelperDb = ManagerhelperDb(this)

        if(preferencias.getRecuerdame() == true){
            irRecomendado()
        }

        binding.iniciarSesion.setOnClickListener {
            validarCrdenciales()
        }

        binding.irregistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }


    }

    private fun validarCrdenciales() {
        val correo = binding.correoSesion.text.toString()
        val passwor = binding.passwordSesion.text.toString()
        if(correo.isNotEmpty() && passwor.isNotEmpty()){
            val resul = managerhelperDb.iniciarSesion(correo, passwor)
            if(resul != null){
                preferencias.saveRecuerdame(binding.checkRecuerdame.isChecked)
                preferencias.saveUsuario(resul.nombre)
                irRecomendado()
            }else{
                Toast.makeText(this, "Credenciales Incrrectas", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Todos lod datos son requieridos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun irRecomendado() {
        startActivity(Intent(this, RecomendadoActivity::class.java))
    }
}