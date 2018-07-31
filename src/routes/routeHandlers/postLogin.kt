package com.up.routes.routeHandlers

import com.up.UserAuthenticator
import com.up.JwtIssuer
import com.up.routes.Login
import com.up.routes.failWithStatusCode
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.http.HttpStatusCode
import io.ktor.locations.post
import io.ktor.request.receiveOrNull
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.postLogin(userAuthenticator: UserAuthenticator, jwtIssuer: JwtIssuer) = post<Login> { _ ->
    failWithStatusCode(HttpStatusCode.BadRequest, {
    val userPasswordCredentials = call.receiveOrNull<UserPasswordCredential>()
    val jwtToken = userPasswordCredentials?.let(userAuthenticator::authenticateUser)?.let(jwtIssuer::createToken) ?: ""
    call.respond(mapOf("bearerToken" to jwtToken))

//    val jwtToken = userPasswordCredentials?.let(userAuthenticator::authenticateUser)?.let(jwtIssuer::createToken)?:""
//    val jwtToken = userAuthenticator.authenticateUser(call.receive<UserPasswordCredential>())?.let(jwtIssuer::createToken)?:""
    //   call.respond(mapOf("bearerToken" to jwtToken))
    })
}
