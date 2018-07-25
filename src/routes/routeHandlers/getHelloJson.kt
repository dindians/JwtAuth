package com.up.routes.routeHandlers

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import routes.HelloJson

fun Route.getHelloJson() = get<HelloJson> { helloJson -> call.respond(mapOf("getHello" to helloJson.name)) }