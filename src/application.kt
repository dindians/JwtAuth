package com.up

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import com.fasterxml.jackson.databind.*
import io.ktor.auth.UserPasswordCredential
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.jwt
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.request.receive
import org.kodein.di.direct
import org.kodein.di.generic.instance

fun main(args: Array<String>): Unit = io.ktor.server.netty.DevelopmentEngine.main(args)

val ApplicationCall.user get() = authentication.principal<User>()!!

fun Application.module() {
    val kodeinAware = KodeinInjector().getKodeinAware(this.environment.config)
    val userStore = kodeinAware.direct.instance<UserStore>()
    val jwtConfigStore = kodeinAware.direct.instance<JwtConfigStore>()
    val jwtAssist = kodeinAware.direct.instance<JwtAssist>()

	install(ContentNegotiation) {
		jackson {
			enable(SerializationFeature.INDENT_OUTPUT)
		}
	}

    authentication {
        jwt("jwt") {
            realm = jwtConfigStore.getRealm()
            verifier(jwtAssist.buildVerifier())
            validate{
                jwtAssist.getUserId(it)?.let(userStore::getUser)
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
            call.respondText(userStore.authenticateUser(call.receive<UserPasswordCredential>())?.let(jwtAssist::createToken)?:"")
        }

		authenticate("jwt") {
			route("authorized"){
                get {
                    val user = call.user
                    call.respondText("${user.name} Authorized",contentType = ContentType.Text.Plain)
                }
            }
		}
	}
}



