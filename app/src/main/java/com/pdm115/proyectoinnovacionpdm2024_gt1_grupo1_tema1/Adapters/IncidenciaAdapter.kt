package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.Incidencia
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.R

class IncidenciaAdapter(private val incidencias: ArrayList<Incidencia>): RecyclerView.Adapter<IncidenciaAdapter.HomeHolder> () {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IncidenciaAdapter.HomeHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_inicio, parent, false)

        return HomeHolder(itemView)
    }

    override fun onBindViewHolder(holder: IncidenciaAdapter.HomeHolder, position: Int) {
        val incidencia: Incidencia = incidencias[position]

        holder.name.text = incidencia.name
        holder.description.text = incidencia.description
        holder.location.text = incidencia.location
//        holder.latitud.text = incidencia.latitud
//        holder.longitud.text = incidencia.longitud
        holder.image.setImageResource(R.drawable.ic_launcher_background)
    }

    override fun getItemCount(): Int {
        return incidencias.size
    }

    public class HomeHolder (itemView: View): RecyclerView.ViewHolder (itemView) {
        val name: TextView = itemView.findViewById(R.id.txt_nombre_incidencia_inicio)
        val location: TextView = itemView.findViewById(R.id.txt_ubicacion_incidencia_inicio)
        val description: TextView = itemView.findViewById(R.id.txt_descripcion_incidencia_inicio)
//        val latitud: TextView = itemView.findViewById(R.id.txt_latitud_incidencia_inicio)
//        val longitud: TextView = itemView.findViewById(R.id.txt_longitud_incidencia_inicio)
        val image: ImageView = itemView.findViewById(R.id.imgv_imagen_incidencia_reportada_inicio)
    }
}