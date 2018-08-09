package com.up

internal data class JwtPayloadDetails(
        val issuer:String,
        val subject:String,
        val audience:String
)