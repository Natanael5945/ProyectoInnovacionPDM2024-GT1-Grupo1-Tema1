package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroParteUno : AppCompatActivity() {

    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var birthDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_parte_uno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bindEditText()

        val prevButton: Button = findViewById(R.id.btn_anterior_registro_parte1)
        val nextButton: Button = findViewById(R.id.btn_siguiente_registro_parte1)
        val ingresaAqui: TextView = findViewById(R.id.txt_ingresa_aqui_registro_parte1)

        prevButton.setOnClickListener {
            goMainActivity()
        }

        nextButton.setOnClickListener {
            verificarDatos()
        }

        ingresaAqui.setOnClickListener {
            goLogin()
        }
    }

    private fun verificarDatos() {
        if (!isValidEditText()) {
            Toast.makeText(this, "Por favor, llene todos los campos", Toast.LENGTH_LONG).show()
            return
        }

        if (!isValidDateFormat()) {
            Toast.makeText(this, "Por favor, ingrese una fecha válida", Toast.LENGTH_LONG).show()
            return
        }

        val intent: Intent = Intent(this, RegistroParteDos::class.java)
        intent.putExtra("fullname",  fullName.text.toString())
        intent.putExtra("email", email.text.toString())
        intent.putExtra("birthDate", birthDate.text.toString())
        startActivity(intent)
    }


    private fun bindEditText() {
        fullName = findViewById(R.id.edtxt_nombre_completo_registro_parte1)
        email = findViewById(R.id.edtxt_correo_registro_parte1)
        birthDate = findViewById(R.id.edtxt_fecha_nacimiento_registro_parte1)
    }

    private fun isValidEditText(): Boolean
    {
        return fullName.text.isNotEmpty() && email.text.isNotEmpty() && birthDate.text.isNotEmpty()
    }
    private fun isValidDateFormat(): Boolean {
        // format: dd/mm/aaaa
        val date = birthDate.text.toString()
        val dateParts = date.split("/")
        var isValid = true
        if (dateParts.size != 3)
        {
            isValid = false
        }
        val day = dateParts[0].toInt()
        val month = dateParts[1].toInt()
        val year = dateParts[2].toInt()
        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 2024)
        {
            isValid = false
        }

        return isValid
    }

    private fun goMainActivity ()
    {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun goLogin()
    {
        val intent: Intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
    }
}