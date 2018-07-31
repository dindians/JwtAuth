package com.up.routes.routeHandlers

import com.up.routes.Feature
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.getFeature() = get<Feature> { feature -> call.respond(mapOf("feature" to feature))}