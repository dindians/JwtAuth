package com.up.routes.routeHandlers

import com.up.routes.Feature
import io.ktor.routing.Route

fun Route.getFeatureOption() {
    getMyMap<Feature.Option>(Route::getFeatureOption)
}

