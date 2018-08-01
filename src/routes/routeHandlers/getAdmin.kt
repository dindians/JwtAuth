package com.up.routes.routeHandlers

import com.up.User
import routes.routeData.Admin
import io.ktor.locations.get
import io.ktor.application.call
import io.ktor.auth.authentication
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route

fun Route.getAdmin() = get<Admin> { _ -> call.respondText("${call.authentication.principal<User>()!!.name} Authorized.",contentType = ContentType.Text.Plain)}
