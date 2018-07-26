package com.up

import com.fasterxml.jackson.databind.SerializationFeature
import com.up.routes.routeHandlers.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTAuthenticationProvider
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.locations.*
import io.ktor.pipeline.PipelineContext
import io.ktor.response.respondText
import io.ktor.routing.*
import org.kodein.di.generic.instance

fun Application.module() {
    val myKodein = KodeinModules.getGlobalWithApplicationConfig(this.environment.config)
    val userProvider: UserProvider by myKodein.instance()
    val jwtPropsProvider: JwtPropsProvider by myKodein.instance()
    val jwtIssuer: JwtIssuer by myKodein.instance()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Locations)

    authentication {
        jwt("jwt") {
            setupJWT(jwtPropsProvider, jwtIssuer, userProvider)
        }
    }

    routing {
        get("/exception") {
            errorAware {
                throw Exception("helloException.")}
        }

        getHello()
        getHelloJson()
        postLogin(userProvider, jwtIssuer)
        authenticate("jwt") {
            getAdmin()
        }
    }
}

private suspend fun <R> PipelineContext<*, ApplicationCall>.errorAware(block: suspend () -> R): R? {
    return try {
        block()
    } catch (e: Exception) {
        call.respondText("""{"error":"$e"}""", ContentType.parse("application/json"), HttpStatusCode.InternalServerError)
        null
    }
}

private fun JWTAuthenticationProvider.setupJWT(jwtPropsProvider: JwtPropsProvider, jwtIssuer: JwtIssuer, userProvider: UserProvider)
{
    realm = jwtPropsProvider.realm
    verifier(jwtIssuer.buildVerifier())
    validate{
        jwtIssuer.getUserId(it)?.let(userProvider::getUser)
    }
}
