package com.up.routes.routeHandlers

import com.up.routes.Feature
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.getFeatureOption() = get<Feature.Option> { featureOption -> call.respond(mapOf("featureOption" to featureOption))}
