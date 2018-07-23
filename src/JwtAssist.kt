package com.up

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import io.ktor.auth.jwt.JWTCredential
import java.util.*

private const val validityInMs = 36_000_00 * 10 // 10 hours
private const val secret = "zAP5MBA4B4Ijz0MZaS48"

class JwtAssist {
    private val algorithm = Algorithm.HMAC512(secret)
    private lateinit var _jwtPayload: JwtPayload

    fun setPayload(jwtPayload: JwtPayload) {
        _jwtPayload = jwtPayload
    }

    fun buildVerifier(): JWTVerifier = JWT
            .require(algorithm)
            .withSubject(_jwtPayload.subject)
            .withIssuer(_jwtPayload.issuer)
            .withAudience(_jwtPayload.audience)
            .build()

    fun createToken(user:User):String = JWT.create()
                    .withSubject(_jwtPayload.subject)
                    .withIssuer(_jwtPayload.issuer)
                    .withAudience(_jwtPayload.audience)
                    .withClaim("id", user.id)
                    .withArrayClaim("countries", user.countries.toTypedArray())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm)

    fun getUserId(jwtCredentials: JWTCredential) = jwtCredentials.payload.getClaim("id")?.asInt()

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}