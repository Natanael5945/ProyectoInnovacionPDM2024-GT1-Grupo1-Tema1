package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models

import com.google.firebase.auth.FirebaseAuth
interface EmailExistsCallback {
    fun onResult(exists: Boolean)
}
class AuthModel() {

    companion object {
       // auth: FirebaseAuth
        lateinit var auth: FirebaseAuth

    }

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }

    fun getCurrentUser() = auth.currentUser

    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    fun deleteUser() {
        auth.currentUser?.delete()
    }

    fun signOut() {
        auth.signOut()
    }

    fun existEmail(email: String, callback: EmailExistsCallback) {
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val result = task.result
                if (result != null) {
                    if (result.signInMethods?.isEmpty() == true) {
                        callback.onResult(false) // El correo no existe
                        return@addOnCompleteListener
                    }
                }
            }
            callback.onResult(true) // El correo existe
        }.addOnFailureListener {
            callback.onResult(false) // En caso de fallo, asumimos que no existe
        }
    }

}