package com.up

import io.ktor.auth.UserPasswordCredential

interface JwtTokenCreator {
    fun validateCredentialsAndCreateToken(userPasswordCredential: UserPasswordCredential):String
}