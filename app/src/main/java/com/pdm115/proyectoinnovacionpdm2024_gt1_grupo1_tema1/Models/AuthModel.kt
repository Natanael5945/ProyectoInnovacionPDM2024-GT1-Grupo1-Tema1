package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Models

import com.google.firebase.auth.FirebaseAuth

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
}