package com.up

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import org.kodein.di.generic.instance

fun Application.module() {
    val kodein = KodeinBindings().getKodein(this.environment.config)
    val userProvider: UserProvider by kodein.instance()
    val jwtPropsProvider: JwtPropsProvider by kodein.instance()
    val jwtIssuer: JwtIssuer by kodein.instance()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    authentication {
        jwt("jwt") {
            realm = jwtPropsProvider.realm
            verifier(jwtIssuer.buildVerifier())
            validate{
                jwtIssuer.getUserId(it)?.let(userProvider::getUser)
            }
        }
    }

    routing {
        get("hello") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("helloJSON") {
            call.respond(mapOf("hello" to "world"))
        }

        post("login") {
            call.respondText(userProvider.authenticateUser(call.receive<UserPasswordCredential>())?.let(jwtIssuer::createToken)?:"")
        }

        authenticate("jwt") {
            route("authorized"){
                get {
                    val user = call.authentication.principal<User>()!!
                    call.respondText("${user.name} Authorized",contentType = ContentType.Text.Plain)
                }
            }
        }
    }
}