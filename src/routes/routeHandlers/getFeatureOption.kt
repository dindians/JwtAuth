package com.up.routes.routeHandlers

import routes.routeData.Feature
import io.ktor.routing.Route

fun Route.getFeatureOption() {
    getMyMap<Feature.Option>(Route::getFeatureOption)
}

