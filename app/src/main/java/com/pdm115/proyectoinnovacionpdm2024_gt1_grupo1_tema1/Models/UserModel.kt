package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models

import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.User

class UserModel {

    companion object {
        const val COLLECTION_USERS = "Users"
        const val FIELD_ID_USER = "idUser"
        const val FIELD_FULLNAME = "fullname"
        const val FIELD_USERNAME = "username"
        const val FIELD_EMAIL = "email"
        const val FIELD_BIRTHDATE = "birthDate"
    }

    private val database = FirebaseDatabase.getInstance().reference // Referencia a la base de datos

    fun createUser(user: User) {
        database.child(COLLECTION_USERS).child(user.idUser).setValue(user)
    }

    fun updateUser(user: User) {
        database.child(COLLECTION_USERS).child(user.idUser).setValue(user)
    }

    fun deleteUser(user: User) {
        database.child(COLLECTION_USERS).child(user.idUser).removeValue()
    }

    fun getUserById(idUser: String) {
        database.child(COLLECTION_USERS).child(idUser)
    }

    fun getAllUsers() {
        database.child(COLLECTION_USERS)
    }

    fun getUserByEmail(email: String) {
        database.child(COLLECTION_USERS).orderByChild(FIELD_EMAIL).equalTo(email)
    }

}