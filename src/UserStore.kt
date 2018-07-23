package com.up

import io.ktor.auth.UserPasswordCredential

interface UserStore {
    fun getUser(id:Int):User?
    fun authenticateUser(userPasswordCredential: UserPasswordCredential):User?
}