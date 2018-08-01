package com.up.routes.routeHandlers

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.routing.Route
import com.up.routes.locationData.Hello
import io.ktor.response.respond

fun Route.getHello() {
    get<Hello> { hello -> call.respond(myMapOf(hello, Route::getHello)) }
//    getMyMap<Hello>(Route::getHello)
}