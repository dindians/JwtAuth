package com.up.routes.routeHandlers

import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import kotlin.reflect.KFunction

internal inline fun <reified T : Any> Route.getMyMap(function: KFunction<Unit>) = get<T> { any -> call.respond(myMapOf(any, function))}

internal inline fun <reified T: Any> myMapOf(any:T, function: KFunction<Unit>):Map<String?,Any?> = mapOf(any::class.simpleName to any, "KotlinClass" to any::class.qualifiedName, "KotlinFunction" to function.toString())
