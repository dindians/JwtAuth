package com.up.routes.routeHandlers

import com.up.routes.locationData.Feature
import io.ktor.routing.Route

fun Route.getFeatureOption() {
    getMyMap<Feature.Option>(Route::getFeatureOption)
}

