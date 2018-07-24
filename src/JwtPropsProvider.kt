package com.up

internal interface JwtPropsProvider {
    val realm: String
    val validityInSeconds: Int
    fun getPayload():JwtPayload
}