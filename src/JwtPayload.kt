package com.up

data class JwtPayload(
        val issuer:String,
        val subject:String,
        val audience:String
)