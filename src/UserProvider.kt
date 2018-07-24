package com.up

import io.ktor.auth.UserPasswordCredential

interface UserProvider {
    fun getUser(id:Int):User?
    fun authenticateUser(userPasswordCredential: UserPasswordCredential):User?
}