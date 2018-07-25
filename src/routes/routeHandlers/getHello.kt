package com.up.routes.routeHandlers

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route
import com.up.routes.Hello
import java.time.Instant

fun Route.getHello() = get<Hello> { hello -> call.respondText("getHello ${hello.name}, the time is now ${Instant.now()}. Thank you.")}