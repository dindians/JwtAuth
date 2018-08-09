package com.up

internal interface JwtConfig {
    val realm: String
    val validityInSeconds: Int
    fun getPayloadConfig():JwtPayloadConfig
}