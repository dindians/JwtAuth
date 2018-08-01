package com.up.routes.routeHandlers

import routes.routeData.MyException
import com.up.routes.tryFailWithStatusCode
import io.ktor.http.HttpStatusCode
import io.ktor.routing.Route
import io.ktor.locations.get

fun Route.getMyException() = get<MyException> { myException -> tryFailWithStatusCode({ throw Exception(myException.message) }, HttpStatusCode.InternalServerError) }