package com.up

import com.fasterxml.jackson.databind.SerializationFeature
import com.up.routes.routeHandlers.*
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTAuthenticationProvider
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import io.ktor.locations.Locations
import io.ktor.routing.routing

fun Application.module() = setupApplication(CoreDependenciesImpl.create(environment.config))

private fun Application.setupApplication(coreDependencies:CoreDependencies)
{
    install(StatusPages){ exception<Throwable> { failWithBadRequest(it) } }
    install(ContentNegotiation) { jackson { enable(SerializationFeature.INDENT_OUTPUT) } }
    install(Locations)

    authentication { jwt("jwt") { setupJWT(coreDependencies.jwtIssuer) } }

    routing {
        getHello()
        getFeature()
        getFeatureOption()
        getFeatureOptionDetails()
        getMyException()
        postLogin(coreDependencies.jwtIssuer)
        authenticate("jwt") { getAdmin() }
    }
}

private fun JWTAuthenticationProvider.setupJWT(jwtIssuer: JwtIssuer)
{
    realm = jwtIssuer.realm
    verifier(jwtIssuer.buildVerifier())
    validate{ jwtIssuer.validate(it) }
}
