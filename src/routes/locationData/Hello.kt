package com.up.routes.locationData

import io.ktor.locations.Location

@Location("/hello/{name}")
internal data class Hello(val name:String, val age: Int=13, val color: String="blue")