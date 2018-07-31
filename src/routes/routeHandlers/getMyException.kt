package com.up.routes.routeHandlers

import com.up.routes.MyException
import com.up.routes.failWithStatusCode
import io.ktor.http.HttpStatusCode
import io.ktor.routing.Route
import io.ktor.locations.get

fun Route.getMyException() = get<MyException> { myException -> failWithStatusCode(HttpStatusCode.InternalServerError,{ throw Exception(myException.message?:"default-message") }) }