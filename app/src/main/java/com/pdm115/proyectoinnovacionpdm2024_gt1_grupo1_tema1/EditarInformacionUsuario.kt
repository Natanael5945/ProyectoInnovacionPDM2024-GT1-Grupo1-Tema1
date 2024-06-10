package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.User
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.UserModel
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Store.UserStore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditarInformacionUsuario.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarInformacionUsuario : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var fullname: EditText
    private lateinit var dui: EditText
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var birthDate: EditText

    private lateinit var userModel: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        userModel = UserModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_editar_informacion_usuario, container, false)

        bindEditText(view)

        val prevButton: Button = view.findViewById(R.id.btn_anterior_configuracion)
        val saveInfo: Button = view.findViewById(R.id.btn_save_nueva_contra)

        prevButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        saveInfo.setOnClickListener {
            saveUser()
        }

        val currentUser:User? = UserStore.getUser()
        if (currentUser != null) {
            fullname.setText(currentUser.fullname)
            dui.setText(currentUser.dui)
            username.setText(currentUser.username)
            email.setText(currentUser.email)
            birthDate.setText(currentUser.birthDate)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditarInformacionUsuario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditarInformacionUsuario().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun saveUser ()
    {

        if (!isValidEditText())
        {
            Toast.makeText(context, "Datos incorrectos",  Toast.LENGTH_LONG).show()
            return
        }

        val currentUser:User? = UserStore.getUser()
        if (currentUser != null) {
            val newUser = User(
                currentUser.idUser,
                fullname.text.toString(),
                username.text.toString(),
                email.text.toString(),
                birthDate.text.toString(),
                dui.text.toString()
            )
            try {
                userModel.updateUser(newUser)
                Toast.makeText(context, "Usuario actualizado",  Toast.LENGTH_LONG).show()
            }
            catch(e: Exception) {
                Toast.makeText(context, "Error al actualizar usuario",  Toast.LENGTH_LONG).show()
                Log.e("Error", e.toString())
            }
        }
    }

    private fun bindEditText(view: View)
    {
        fullname = view.findViewById(R.id.edtxt_nombre_completo_editar_infop)
        dui = view.findViewById(R.id.edtxt_dui)
        username = view.findViewById(R.id.edtxt_nombre_usuario_editar_infop)
        email = view.findViewById(R.id.edtxt_correo_editar_infop)
        birthDate = view.findViewById(R.id.edtxt_fecha_nacimiento_editar_infop)
    }

    private fun isValidEditText(): Boolean {
        return fullname.text.isNotEmpty() && dui.text.isNotEmpty() && username.text.isNotEmpty() && email.text.isNotEmpty() && birthDate.text.isNotEmpty()
    }
}