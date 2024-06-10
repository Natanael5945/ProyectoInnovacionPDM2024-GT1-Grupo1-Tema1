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
import androidx.fragment.app.findFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.Incidencia
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.User
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.IncidenciaModel
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Store.UserStore
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReportarIncidencia.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportarIncidencia : Fragment(), OnMapReadyCallback {


    private lateinit var name: EditText
    private lateinit var description: EditText
    private lateinit var location: EditText
    private var latitude: String = ""
    private var longitude: String = ""

    private var user: User? = null
    private lateinit var incidenciaModel: IncidenciaModel

    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

        user = UserStore.getUser()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reportar_incidencia, container, false)
        bindEditText(view)
        incidenciaModel = IncidenciaModel()
        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as? SupportMapFragment
        mapFragment?.getMapAsync {
            map = it
        }

        val guardarIncidencia: Button = view.findViewById(R.id.guardar_incidencia)

        guardarIncidencia.setOnClickListener {
            saveData()
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
         * @return A new instance of fragment ReportarIncidencia.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReportarIncidencia().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun bindEditText(view: View)
    {
        name = view.findViewById(R.id.edtxt_nombre_incidencia)
        description = view.findViewById(R.id.edtxt_descripcion_incidencia)
        location = view.findViewById(R.id.edtxt_direccion_incidencia)
    }
    override fun onMapReady(p0: GoogleMap) {
        map = p0
    }

    private fun verificarCampos( ): Boolean
    {
        if (name.text.isEmpty() || description.text.isEmpty() || location.text.isEmpty())
        {
            return false
        }
        return true
    }

    private fun saveData()
    {
        if (!verificarCampos())
        {
            Toast.makeText(activity, "Datos incorrectos",  Toast.LENGTH_LONG).show()
            return
        }

        if (user != null) {
            var nuevaIncidencia: Incidencia = Incidencia(
                UUID.randomUUID().toString(),
                name.text.toString(),
                description.text.toString(),
                location.text.toString(),
                longitude,
                latitude,
                null,
                user!!.idUser
            )
            try {
                incidenciaModel.createIncidencia(nuevaIncidencia)
                Toast.makeText(activity, "Incidencia guardada",  Toast.LENGTH_LONG).show()
                clearAll()
            } catch (e: Exception) {
                Toast.makeText(activity, "Error al guardar incidencia",  Toast.LENGTH_LONG).show()
                Log.e("Error", e.toString())
            }
        }
        return
    }

    private fun clearAll ()
    {
        name.text.clear()
        description.text.clear()
        location.text.clear()
    }
}