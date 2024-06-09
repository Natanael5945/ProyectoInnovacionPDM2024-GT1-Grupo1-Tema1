package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val registerButton: Button = findViewById(R.id.btn_registrarse)
        val loginButton: Button = findViewById(R.id.btn_iniciar_sesion)

        registerButton.setOnClickListener {
            goRegister()
        }

        loginButton.setOnClickListener {
            goLogin()
        }
    }


    private fun goRegister ()
    {
        val intent: Intent = Intent(this, RegistroParteUno::class.java)
        startActivity(intent)
    }

    private fun goLogin ()
    {
        val intent: Intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
    }
}