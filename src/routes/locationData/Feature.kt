package com.up.routes.locationData

import io.ktor.locations.Location

@Location("/feature/{featureId}")
internal data class Feature(val featureId:Int) {
    @Location("/option/{optionId}")
    data class Option(val feature: Feature, val optionId:Int) {
        @Location("/details")
        data class Details(val option: Option)
    }
}