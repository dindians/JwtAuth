package com.up.routes.routeHandlers

import com.up.routes.Feature
import io.ktor.routing.Route

fun Route.getFeatureOption() {
    getMyMap<Feature.Option>(Route::getFeatureOption)
//    get<Feature.Option> { featureOption -> call.respond(myMapOf(featureOption, Route::getFeatureOption)) }

    /* todo: does not catch the exception
    get<Feature.Option> {
        featureOption ->
        failWithStatusCode(HttpStatusCode.InternalServerError,{
            call.respond(myMapOf(featureOption, Route::getFeatureOption))
        })
    }
    */
}

