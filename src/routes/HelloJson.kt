package routes

import io.ktor.locations.Location

@Location("/helloJson/{name}")
data class HelloJson(val name:String)