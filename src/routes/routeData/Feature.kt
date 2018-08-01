package routes.routeData

import io.ktor.locations.Location

@Location("/feature/{featureId}")
data class Feature(val featureId:Int) {
    @Location("/option/{optionId}")
    data class Option(val feature: Feature, val optionId:Int) {
        @Location("/details")
        data class Details(val option: Option)
    }
}