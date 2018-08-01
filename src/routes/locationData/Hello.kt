package com.up.routes.locationData

import io.ktor.locations.Location

@Location("/hello/{name}")
data class Hello(val name:String)