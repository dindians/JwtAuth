package com.up

import com.auth0.jwt.JWTVerifier
import io.ktor.auth.jwt.JWTCredential

interface JwtIssuer {
    val realm: String
    fun buildVerifier(): JWTVerifier
    fun createToken(user:User):String
    fun getUserId(jwtCredentials: JWTCredential): Int?
}