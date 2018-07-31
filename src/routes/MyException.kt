package com.up.routes

import io.ktor.locations.Location

@Location("/exception/{message}")
data class MyException(val message:String?=null)