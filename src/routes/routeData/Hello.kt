package com.up.routes.routeData

import io.ktor.locations.Location

@Location("/hello/{name}")
data class Hello(val name:String)