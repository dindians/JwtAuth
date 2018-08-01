package com.up.routes.routeHandlers

import routes.routeData.Feature
import io.ktor.routing.Route

fun Route.getFeatureOptionDetails() {
    getMyMap<Feature.Option.Details>(Route::getFeatureOptionDetails)
//    get<Feature.Option.Details> { featureOptionDetails -> call.respond(myMapOf(featureOptionDetails, Route::getFeatureOptionDetails)) }
}
