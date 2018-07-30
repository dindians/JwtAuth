package com.up.routes.routeHandlers

import com.up.UserAuthenticator
import com.up.JwtIssuer
import com.up.routes.Login
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.postLogin(userAuthenticator: UserAuthenticator, jwtIssuer: JwtIssuer) = post<Login> { _ ->
    call.respond(mapOf("bearerToken" to (userAuthenticator.authenticateUser(call.receive<UserPasswordCredential>())?.let(jwtIssuer::createToken)?:"")))
}