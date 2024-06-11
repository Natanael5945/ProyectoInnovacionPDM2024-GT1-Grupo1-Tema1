package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Adapters.IncidenciaAdapter
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.Incidencia
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models.IncidenciaModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Inicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class Inicio : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var incidenciaAdapter: IncidenciaAdapter
    private lateinit var incidencias: ArrayList<Incidencia>
    private lateinit var incidenciaModel: IncidenciaModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        incidenciaModel = IncidenciaModel()
        incidencias = arrayListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_inicio, container, false)
        val crearIncidenciaButton: Button = view.findViewById(R.id.btn_reportar_incidencia_inicio)

        crearIncidenciaButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.frame_contenedor, ReportarIncidencia())
                addToBackStack(null)
            }
        }

        val revycleView: RecyclerView = view.findViewById(R.id.recycler_view_incidencias_ya_reportadas_inicio)
        revycleView.layoutManager = LinearLayoutManager(activity)
        revycleView.setHasFixedSize(true)

        incidenciaAdapter = IncidenciaAdapter(incidencias)

        revycleView.adapter = incidenciaAdapter

        EventChangeListener()

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun EventChangeListener() {
        incidenciaModel.getAllIncidencias {
            incidencias.clear()
            incidencias.addAll(it)
            incidenciaAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Inicio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Inicio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}