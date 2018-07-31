package com.up.routes

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.pipeline.PipelineContext
import io.ktor.response.respond

internal suspend fun <R> PipelineContext<*, ApplicationCall>.failWithStatusCode(httpStatusCode:HttpStatusCode, block: suspend () -> R): R? {
    return try { block()
    } catch (e: Exception) {
        call.response.status(httpStatusCode)
        call.respond(mapOf("exceptionType" to e::class.qualifiedName, "exceptionDetails" to e))
        null
    }
}