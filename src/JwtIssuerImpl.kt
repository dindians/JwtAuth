package com.up

import com.auth0.jwt.*
import io.ktor.auth.jwt.JWTCredential
import java.util.*

internal class JwtIssuerImpl(jwtPropsProvider:JwtPropsProvider, jwtSigningAlgorithmProvider:JwtSigningAlgorithmProvider) : JwtIssuer{
    private val validityInMs = 36_000_00 * 10 // 10 hours
    private val algorithm = jwtSigningAlgorithmProvider.getAlgorithm()
    private val jwtPayload: JwtPayload = jwtPropsProvider.getPayload()

    override fun buildVerifier(): JWTVerifier = JWT
            .require(algorithm)
            .withSubject(jwtPayload.subject)
            .withIssuer(jwtPayload.issuer)
            .withAudience(jwtPayload.audience)
            .build()

    override fun createToken(user:User):String = JWT.create()
                    .withSubject(jwtPayload.subject)
                    .withIssuer(jwtPayload.issuer)
                    .withAudience(jwtPayload.audience)
                    .withClaim("id", user.id)
                    .withArrayClaim("countries", user.countries.toTypedArray())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm)

    override fun getUserId(jwtCredentials: JWTCredential) = jwtCredentials.payload.getClaim("id")?.asInt()

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}