package com.up.routes.routeHandlers

import com.up.routes.locationData.Feature
import io.ktor.routing.Route

fun Route.getFeature(){
    getMyMap<Feature>(Route::getFeature)
}
