package com.up

data class JwtPayloadDetails(
        val issuer:String,
        val subject:String,
        val audience:String
)