package com.up.routes.routeHandlers

import com.up.UserAuthenticator
import com.up.JwtIssuer
import com.up.routes.Login
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Route

fun Route.postLogin(userAuthenticator: UserAuthenticator, jwtIssuer: JwtIssuer) = post<Login> { _ ->
    call.respondText(userAuthenticator.authenticateUser(call.receive<UserPasswordCredential>())?.let(jwtIssuer::createToken)?:"")
}