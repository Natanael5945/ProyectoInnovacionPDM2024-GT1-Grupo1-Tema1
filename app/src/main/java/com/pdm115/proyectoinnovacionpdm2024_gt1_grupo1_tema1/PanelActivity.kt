package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1

import com.google.firebase.database.Query
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.User
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.AuthModel
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.UserModel
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Store.UserStore

class PanelActivity : AppCompatActivity() {

    lateinit var menuNavegacion: BottomNavigationView

    private lateinit var userModel: UserModel
    private lateinit var auth: AuthModel

    private val mOnNavegacionMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when(item.itemId){
            R.id.item_inicio ->{
                cambioDeFragmento(Inicio())
                return@OnNavigationItemSelectedListener true
            }

            R.id.item_notificacion ->{
                cambioDeFragmento(Notificaciones())
                return@OnNavigationItemSelectedListener true
            }

            R.id.item_incidencias ->{
                cambioDeFragmento(Incidencias())
                return@OnNavigationItemSelectedListener true
            }

            R.id.item_configuracion ->{
                cambioDeFragmento(Configuraciones())
                return@OnNavigationItemSelectedListener true
            }

        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_panel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        auth = AuthModel()
        userModel = UserModel()

        val email: String? = auth.getCurrentUser()?.email
        Log.d("USER", "Email: $email")
        if (email != null) {
            userModel.getUserByEmail(email) { user ->
                if (user != null) {
                    println("User found: $user")
                    UserStore.setUser(user)
                } else {
                    println("User not found")
                }
            }
        } else {
            Log.d("USER", "No se encontro el email")
        }

        menuNavegacion = findViewById(R.id.menu_inferior)
        menuNavegacion.setOnNavigationItemSelectedListener(mOnNavegacionMenu)
        //Fragmento con el cual se mostrara en la vista sino se pone no sale nada al comienzo hasta dar clic
        cambioDeFragmento(Inicio())
    }

    private fun FragmentActivity.cambioDeFragmento(fragmento: Fragment){

        supportFragmentManager.commit {
            replace(R.id.frame_contenedor, fragmento)
            setReorderingAllowed(true)
            addToBackStack(null)
        }

        /*
        //Versión larga implementar en dado caso no funcione la otra
        supportFragmentManager.commit {
            replace<Habitaciones>(R.id.frame_contenedor)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        */

    }
}