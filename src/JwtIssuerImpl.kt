package com.up

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import io.ktor.auth.Principal
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.jwt.JWTCredential
import java.util.*

internal class JwtIssuerImpl(jwtPropsProvider:JwtPropsProvider, jwtSigningAlgorithmProvider:JwtSigningAlgorithmProvider, private val userAuthenticator:UserAuthenticator, private val userProvider:UserProvider) : JwtIssuer{
    private val validityInMs = jwtPropsProvider.validityInSeconds
    private val algorithm = jwtSigningAlgorithmProvider.getAlgorithm()
    private val jwtPayloadDetails: JwtPayloadDetails = jwtPropsProvider.getPayloadDetails()

    override val realm = jwtPropsProvider.realm

    // The JWTVerifier class holds the verify method to assert that a given Token has not only a proper JWT format, but also it's signature matches.
    override fun buildVerifier(): JWTVerifier = JWT
            .require(algorithm)
            .withSubject(jwtPayloadDetails.subject)
            .withIssuer(jwtPayloadDetails.issuer)
            .withAudience(jwtPayloadDetails.audience)
            .build()

    override fun validateCredentialsAndCreateToken(userPasswordCredential: UserPasswordCredential) = userPasswordCredential.let(userAuthenticator::authenticateUser)?.let(this::createToken)?: ""

    override fun validate(jwtCredentials: JWTCredential): Principal? = getUserId(jwtCredentials)?.let(userProvider::getUser)

    private fun createToken(user:User):String = JWT.create()
                    .withSubject(jwtPayloadDetails.subject)
                    .withIssuer(jwtPayloadDetails.issuer)
                    .withAudience(jwtPayloadDetails.audience)
                    .withClaim("id", user.id)
                    .withArrayClaim("countries", user.countries.toTypedArray())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm)

    private fun getUserId(jwtCredentials: JWTCredential) = jwtCredentials.payload.getClaim("id")?.asInt()

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}