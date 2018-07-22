package com.up

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.jwt.JWTCredential
import java.util.*

object JwtAssist {
    private const val validityInMs = 36_000_00 * 10 // 10 hours
    private const val secret = "zAP5MBA4B4Ijz0MZaS48"
    private val algorithm = Algorithm.HMAC512(secret)

    fun buildVerifier(jwtPayload:JwtPayload): JWTVerifier = JWT
            .require(algorithm)
            .withSubject(jwtPayload.subject)
            .withIssuer(jwtPayload.issuer)
            .withAudience(jwtPayload.audience)
            .build()

    fun authenticateUser(userPasswordCredential: UserPasswordCredential) = userPasswordCredential.getUser()

    fun createToken(jwtPayload:JwtPayload, user:User):String = JWT.create()
                    .withSubject(jwtPayload.subject)
                    .withIssuer(jwtPayload.issuer)
                    .withAudience(jwtPayload.audience)
                    .withClaim("id", user.id)
                    .withArrayClaim("countries", user.countries.toTypedArray())
                    .withExpiresAt(getExpiration())
                    .sign(algorithm)

    private fun UserPasswordCredential.getUser():User? = if(name.equals("anton") && password.equals("me")) getUser(1) else null

    private fun JWTCredential.getUser() : User? = getUser( payload.getClaim("id").asInt())

    private fun getUser(id:Int):User? = when (id) {
            1 -> User(1, "anton", listOf("NL", "BE"))
            else -> null
        }

    fun validateCredentials(jwtCredentials: JWTCredential, jwtPayload:JwtPayload): User?
    {
        val user = jwtCredentials.getUser()
        return if (jwtCredentials.payload.getClaim("id")?.asInt() == 1 && jwtCredentials.payload.audience.contains(jwtPayload.audience)) user else null
    }

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}