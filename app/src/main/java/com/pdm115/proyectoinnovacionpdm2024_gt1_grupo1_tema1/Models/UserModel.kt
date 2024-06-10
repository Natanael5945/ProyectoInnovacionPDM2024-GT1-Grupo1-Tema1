package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
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

    private val database = FirebaseDatabase.getInstance().getReference(COLLECTION_USERS) // Referencia a la base de datos

    fun createUser(user: User) {
        database.child(user.idUser).setValue(user)
    }

    fun updateUser(user: User) {
        database.child(user.idUser).setValue(user)
    }

    fun deleteUser(user: User) {
        database.child(user.idUser).removeValue()
    }

    fun getUserById(idUser: String) {
        database.child(idUser)
    }

    fun getAllUsers() {
        database.orderByChild(FIELD_FULLNAME)
    }

    fun getUserByEmail(email: String, callback: (User?) -> Unit) {
        database.orderByChild(FIELD_EMAIL).equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        callback(user)
                        return
                    }
                }
                callback(null) // No user found
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null) // Handle error
            }
        })
    }

}