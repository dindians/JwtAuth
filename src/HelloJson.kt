package com.up

import io.ktor.locations.Location

@Location("/helloJson/{name}")
data class helloJson(val name:String)