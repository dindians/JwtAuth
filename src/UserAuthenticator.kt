package com.up

import io.ktor.auth.UserPasswordCredential

interface UserAuthenticator {
    fun authenticateUser(userPasswordCredential: UserPasswordCredential):User?
}