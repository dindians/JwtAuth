package com.up

import com.auth0.jwt.JWTVerifier
import io.ktor.auth.Principal
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.jwt.JWTCredential

interface JwtIssuer: JwtTokenCreator {
    val realm: String
    fun buildVerifier(): JWTVerifier
    override fun validateCredentialsAndCreateToken(userPasswordCredential: UserPasswordCredential):String
    fun validate(jwtCredentials: JWTCredential): Principal?
}
