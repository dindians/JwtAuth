package com.up.routes.routeHandlers

import com.up.UserAuthenticator
import com.up.JwtIssuer
import com.up.routes.locationData.Login
import com.up.tryFailWithStatusCode
import io.ktor.application.call
import io.ktor.auth.UserPasswordCredential
import io.ktor.http.HttpStatusCode
import io.ktor.locations.post
import io.ktor.request.receiveOrNull
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.postLogin(userAuthenticator: UserAuthenticator, jwtIssuer: JwtIssuer) = post<Login> { _ ->
    tryFailWithStatusCode({
    val userPasswordCredentials = call.receiveOrNull<UserPasswordCredential>()
    val jwtToken = userPasswordCredentials?.let(userAuthenticator::authenticateUser)?.let(jwtIssuer::createToken) ?: ""
    call.respond(mapOf("bearerToken" to jwtToken))

//    val jwtToken = userPasswordCredentials?.let(userAuthenticator::authenticateUser)?.let(jwtIssuer::createToken)?:""
//    val jwtToken = userAuthenticator.authenticateUser(call.receive<UserPasswordCredential>())?.let(jwtIssuer::createToken)?:""
    //   call.respond(mapOf("bearerToken" to jwtToken))
    }, HttpStatusCode.BadRequest)
}
