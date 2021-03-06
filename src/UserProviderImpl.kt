package com.up

import io.ktor.auth.UserPasswordCredential

internal class UserProviderImpl : UserProvider, UserAuthenticator{
    private fun UserPasswordCredential.getUser():User? = if(name == "anton" && password == "me") getUser(1) else null

    override fun getUser(id:Int):User? = when (id) {
        1 -> User(1, "anton", listOf("NL", "BE"))
        else -> null
    }

    override fun authenticateUser(userPasswordCredential: UserPasswordCredential) = userPasswordCredential.getUser()
}