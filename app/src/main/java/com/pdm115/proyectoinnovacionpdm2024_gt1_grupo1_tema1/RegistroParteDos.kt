package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.User
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.AuthModel
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.UserModel
import java.util.UUID

class RegistroParteDos : AppCompatActivity() {

    private lateinit var fullname: String
    private lateinit var email: String
    private lateinit var birthDate: String
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var passwordConfirm: EditText

    private lateinit var auth: AuthModel
    private lateinit var userModel: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_parte_dos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = AuthModel()
        exitLogin()

        val myIntent = intent

        fullname = myIntent.getStringExtra("fullname").toString()
        email = myIntent.getStringExtra("email").toString()
        birthDate = myIntent.getStringExtra("birthDate").toString()

        bindEditText()

        val prevButton: Button = findViewById(R.id.btn_anterior_registro_parte2)
        val nextButton: Button = findViewById(R.id.btn_registrarme_registro_parte2)
        val goLoginText: TextView = findViewById(R.id.txt_ingresa_aqui_registro_parte2)

        prevButton.setOnClickListener {
            goRegistroParteUno()
        }

        goLoginText.setOnClickListener {
            goLogin()
        }

        nextButton.setOnClickListener {
            sendData()
        }

        userModel = UserModel()
    }

    private fun sendData () {
        if (!isValidData())
        {
            Toast.makeText(this, "Por favor, llene todos los campos", Toast.LENGTH_LONG).show()
            return
        }
        if (!isPasswordConfirm())
        {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            return
        }

        val user: User = User(
            UUID.randomUUID().toString(),
            fullname,
            email,
            birthDate,
            username.text.toString()
        )

        try {
            auth.createUserWithEmailAndPassword(email, password.text.toString())
            userModel.createUser(user)
            Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_LONG).show()
            goLogin()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show()
            auth.deleteUser()
            Log.e("Error", e.message.toString())

        }
    }
    private fun bindEditText()
    {
        username = findViewById(R.id.edtxt_nombre_usuario_parte2)
        password = findViewById(R.id.edtxt_contrasenia_parte2)
        passwordConfirm = findViewById(R.id.edtxt_repetir_contrasenia)
    }

    
    private fun isPasswordConfirm() : Boolean
    {
        return password.text.toString() == passwordConfirm.text.toString()
    }

    private fun isValidData(): Boolean
    {
        return username.text.isNotEmpty() && password.text.isNotEmpty() && passwordConfirm.text.isNotEmpty()
    }

    private fun goRegistroParteUno ()
    {
        val intent: Intent = Intent(this, RegistroParteUno::class.java)
        intent.putExtra("fullname", fullname)
        intent.putExtra("email", email)
        intent.putExtra("birthDate", birthDate)
        startActivity(intent)
    }

    private fun goLogin()
    {
        val intent: Intent = Intent(this, IniciarSesion::class.java)
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