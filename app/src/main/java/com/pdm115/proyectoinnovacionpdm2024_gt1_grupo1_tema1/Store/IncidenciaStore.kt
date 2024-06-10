package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Store

import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.Incidencia

object IncidenciaStore {
    private var incidencia: Incidencia? = null
    private var incidencias: ArrayList<Incidencia>? = null

    fun setIncidencia(incidencia: Incidencia) {
        this.incidencia = incidencia
    }

    fun getIncidencia(): Incidencia? {
        return incidencia
    }

    fun clearIncidencia() {
        incidencia = null
    }

    fun setIncidencias(incidencias: ArrayList<Incidencia>) {
        this.incidencias = incidencias
    }

    fun getIncidencias(): ArrayList<Incidencia>? {
        return incidencias
    }

    fun clearIncidencias() {
        incidencias = null
    }
}