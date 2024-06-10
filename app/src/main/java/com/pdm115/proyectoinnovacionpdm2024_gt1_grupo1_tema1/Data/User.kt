package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var idUser: String,
    val fullname: String,
    val username: String,
    val email: String,
    val birthDate: String,
    val dui: String?
):Parcelable {
    constructor():this("","","","","", null)
}
