package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Incidencia (
    var idIncidencia: String,
    var name: String,
    var description: String,
    var location: String,
    var longitud: String?,
    var latitud: String?,
    var image: String?,
    var userId: String,
):Parcelable {
    constructor():this("","","","",null, null, null, "")
}

