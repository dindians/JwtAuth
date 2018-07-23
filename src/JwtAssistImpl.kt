package com.up

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import io.ktor.auth.jwt.JWTCredential
import java.util.*


internal class JwtAssistImpl : IJwtAssist{
    private val validityInMs = 36_000_00 * 10 // 10 hours
    private val secret = "zAP5MBA4B4Ijz0MZaS48"
    private val algorithm = Algorithm.HMAC512(secret)
    private lateinit var _jwtPayload: JwtPayload

    override fun setPayload(jwtPayload: JwtPayload) {
        _jwtPayload = jwtPayload
    }

    override fun buildVerifier(): JWTVerifier = JWT
            .require(algorithm)
            .withSubject(_jwtPayload.subject)
            .withIssuer(_jwtPayload.issuer)
            .withAudience(_jwtPayload.audience)
            .build()

    override fun createToken(user:User):String = JWT.create()
                    .withSubject(_jwtPayload.subject)
                    .withIssuer(_jwtPayload.issuer)
                    .withAudience(_jwtPayload.audience)
                    .withClaim("id", user.id)
                    .withArrayClaim("countries", user.countries.toTypedArray())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm)

    override fun getUserId(jwtCredentials: JWTCredential) = jwtCredentials.payload.getClaim("id")?.asInt()

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}