package com.up

internal data class JwtPayload(
        val issuer:String,
        val subject:String,
        val audience:String
)