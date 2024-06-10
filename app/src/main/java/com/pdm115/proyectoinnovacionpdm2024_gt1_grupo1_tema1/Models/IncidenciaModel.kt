package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.Incidencia

class IncidenciaModel {
    companion object {
        const val COLLECTION_USERS = "Incidencias"
        const val FIELD_ID_USER = "idIncidencia"
        const val FIELD_NAME = "name"
        const val FIELD_DESCRIPTION = "description"
        const val FIELD_LOCATION = "location"
        const val FIELD_LONGITUD = "longitud"
        const val FIELD_LATITUD = "latitud"
    }

    private val database = FirebaseDatabase.getInstance().getReference(COLLECTION_USERS) // Referencia a la base de datos

    fun createIncidencia(incidencia: Incidencia) {
        database.child(incidencia.idIncidencia).setValue(incidencia)
    }

    fun updateIncidencia(incidencia: Incidencia) {
        database.child(incidencia.idIncidencia).setValue(incidencia)
    }

    fun deleteIncidencia(incidencia: Incidencia) {
        database.child(incidencia.idIncidencia).removeValue()
    }

    fun getIncidenciaById(idIncidencia: String, callback: (Incidencia?) -> Unit) {
        database.child(idIncidencia).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val incidencia = snapshot.getValue(Incidencia::class.java)
                    callback(incidencia)
                    return
                }
                callback(null)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null) // Handle error
            }
        })
    }

    fun getAllIncidencias( callback: (ArrayList<Incidencia>) -> Unit) {
        database.orderByChild(FIELD_NAME).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val incidencias = ArrayList<Incidencia>()
                if (snapshot.exists()) {
                    for (incidenciaSnapshot in snapshot.children) {
                        val incidencia = incidenciaSnapshot.getValue(Incidencia::class.java)
                        incidencias.add(incidencia!!)
                    }
                }
                callback(incidencias)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(ArrayList()) // Handle error
            }
        })
    }

    fun getPaginateIncidencias (start: Int, end: Int, callback: (ArrayList<Incidencia>) -> Unit){
        database.orderByChild(FIELD_NAME).startAt(start.toDouble()).endAt(end.toDouble()).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val incidencias = ArrayList<Incidencia>()
                if (snapshot.exists()) {
                    for (incidenciaSnapshot in snapshot.children) {
                        val incidencia = incidenciaSnapshot.getValue(Incidencia::class.java)
                        incidencias.add(incidencia!!)
                    }
                }
                callback(incidencias)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(ArrayList()) // Handle error
            }
        })
    }
}