package com.up.routes.routeHandlers

import com.up.JwtIssuer
import com.up.UserProvider
import com.up.routes.Login
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Route

fun Route.postLogin(userProvider: UserProvider, jwtIssuer: JwtIssuer) = post<Login> { _ ->
    call.respondText(userProvider.authenticateUser(call.receive<UserPasswordCredential>())?.let(jwtIssuer::createToken)?:"")
}