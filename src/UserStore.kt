package com.up

import io.ktor.auth.UserPasswordCredential

internal class UserStore {
    private fun UserPasswordCredential.getUser():User? = if(name.equals("anton") && password.equals("me")) getUser(1) else null

    fun getUser(id:Int):User? = when (id) {
        1 -> User(1, "anton", listOf("NL", "BE"))
        else -> null
    }

    fun authenticateUser(userPasswordCredential: UserPasswordCredential) = userPasswordCredential.getUser()
}