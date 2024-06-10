package com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Store

import com.pdm115.proyectoinnovacionpdm2024_gt1_grupo1_tema1.Data.User

object UserStore {
    private var user: User? = null

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User? {
        return user
    }

    fun clearUser() {
        user = null
    }
}