package com.up.routes.routeHandlers

import com.up.routes.locationData.MyException
import com.up.tryFailWithStatusCode
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.routing.Route

internal fun Route.getMyException() = get<MyException> { tryFailWithStatusCode({ throw Exception(it.message) }, HttpStatusCode.InternalServerError) }