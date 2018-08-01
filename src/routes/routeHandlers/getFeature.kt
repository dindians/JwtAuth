package com.up.routes.routeHandlers

import routes.routeData.Feature
import io.ktor.routing.Route

fun Route.getFeature(){
    getMyMap<Feature>(Route::getFeature)
}
