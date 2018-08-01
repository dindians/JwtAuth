package routes.routeData

import io.ktor.locations.Location

@Location("/exception/{message}")
data class MyException(val message:String)