package routes

import io.ktor.locations.Location

@Location("/hello/{name}")
data class Hello(val name:String)