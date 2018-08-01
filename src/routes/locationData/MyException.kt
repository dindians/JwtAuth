package com.up.routes.locationData

import io.ktor.locations.Location

@Location("/exception/{message}")
data class MyException(val message:String)