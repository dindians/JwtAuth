package com.up

import routes.Hello
import com.fasterxml.jackson.databind.SerializationFeature
import com.up.routes.routeHandlers.hello
import com.up.routes.routeHandlers.helloJson
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
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import org.kodein.di.generic.instance
import routes.HelloJson
import java.time.Instant.now

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

    install(Locations)

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
        hello()
        helloJson()

        get("helloWorld") {
            call.respondText("Hello World!", contentType = ContentType.Text.Plain)
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
