package com.up

internal data class JwtPayloadConfig(
        val issuer:String,
        val subject:String,
        val audience:String
)