package com.up

import com.auth0.jwt.*
import io.ktor.auth.jwt.JWTCredential
import java.util.*

internal class JwtIssuerImpl(jwtPropsProvider:JwtPropsProvider, jwtSigningAlgorithmProvider:JwtSigningAlgorithmProvider) : JwtIssuer{
    private val validityInMs = jwtPropsProvider.validityInSeconds
    private val algorithm = jwtSigningAlgorithmProvider.getAlgorithm()
    private val jwtPayloadDetails: JwtPayloadDetails = jwtPropsProvider.getPayloadDetails()

    // The JWTVerifier class holds the verify method to assert that a given Token has not only a proper JWT format, but also it's signature matches.
    override fun buildVerifier(): JWTVerifier = JWT
            .require(algorithm)
            .withSubject(jwtPayloadDetails.subject)
            .withIssuer(jwtPayloadDetails.issuer)
            .withAudience(jwtPayloadDetails.audience)
            .build()

    override fun createToken(user:User):String = JWT.create()
                    .withSubject(jwtPayloadDetails.subject)
                    .withIssuer(jwtPayloadDetails.issuer)
                    .withAudience(jwtPayloadDetails.audience)
                    .withClaim("id", user.id)
                    .withArrayClaim("countries", user.countries.toTypedArray())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm)

    override fun getUserId(jwtCredentials: JWTCredential) = jwtCredentials.payload.getClaim("id")?.asInt()

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}