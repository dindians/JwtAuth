package com.up

import io.ktor.auth.UserPasswordCredential

internal interface UserAuthenticator {
    fun authenticateUser(userPasswordCredential: UserPasswordCredential):User?
}