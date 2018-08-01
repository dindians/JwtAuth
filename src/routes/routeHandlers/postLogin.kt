package com.up.routes.routeHandlers

import com.up.UserAuthenticator
import com.up.JwtIssuer
import com.up.routes.locationData.Login
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.locations.post
import io.ktor.request.receiveOrNull
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.postLogin(userAuthenticator: UserAuthenticator, jwtIssuer: JwtIssuer) = post<Login> {
    call.respond(mapOf("bearerToken" to (call.receiveOrNull<UserPasswordCredential>()?.let(userAuthenticator::authenticateUser)?.let(jwtIssuer::createToken) ?: "")))
}
