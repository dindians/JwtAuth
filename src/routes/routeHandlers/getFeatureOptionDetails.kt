package com.up.routes.routeHandlers

import com.up.routes.locationData.Feature
import io.ktor.routing.Route

internal fun Route.getFeatureOptionDetails() {
    getMyMap<Feature.Option.Details>(Route::getFeatureOptionDetails)
//    get<Feature.Option.Details> { featureOptionDetails -> call.respond(myMapOf(featureOptionDetails, Route::getFeatureOptionDetails)) }
}
