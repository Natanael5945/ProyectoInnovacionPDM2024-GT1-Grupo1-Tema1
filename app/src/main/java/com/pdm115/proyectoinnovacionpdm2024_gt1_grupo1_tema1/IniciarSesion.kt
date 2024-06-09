package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.AuthModel

class IniciarSesion : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var auth: AuthModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciar_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = AuthModel()
        exitLogin()
        bindEditText()

        val prevButton: Button = findViewById(R.id.btn_anterior_login)
        val nextButton: Button = findViewById(R.id.btn_ingresar_login)

        prevButton.setOnClickListener {
            goMainActivity()
        }

        nextButton.setOnClickListener {
            sendData()
        }

    }

    private fun sendData ()
    {
        if (!isValidEditText())
        {
            Toast.makeText(this, "Datos incorrectos",  Toast.LENGTH_LONG).show()
            return
        }

        if (!isValidEmail())
        {
            Toast.makeText(this, "Correo invalido",  Toast.LENGTH_LONG).show()
            return
        }

        try {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            Toast.makeText(this, "Inicio de sesion exitoso",  Toast.LENGTH_LONG).show()
            goMainActivity()
        }
        catch(e: Exception) {
            Toast.makeText(this, "Error al iniciar sesion",  Toast.LENGTH_LONG).show()
            Log.e("Error", e.toString())
        }

    }
    private fun isValidEmail(): Boolean {
        val email = email.text.toString()
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidEditText(): Boolean {
        if (email.text.isEmpty()) {
            email.error = "Campo requerido"
            return false
        }
        if (password.text.isEmpty()) {
            password.error = "Campo requerido"
            return false
        }
        return true
    }

    private fun bindEditText()
    {
        email = findViewById(R.id.edtxt_correo)
        password = findViewById(R.id.edtxt_contraseña)
    }

    private fun goMainActivity ()
    {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun exitLogin()
    {
        if (auth.getCurrentUser() != null)
        {
            val intent: Intent = Intent(this, PanelActivity::class.java)
            startActivity(intent)
        }
    }

}