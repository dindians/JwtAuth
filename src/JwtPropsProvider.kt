package com.up

interface JwtPropsProvider {
    val realm: String
    val validityInSeconds: Int
    fun getPayloadDetails():JwtPayloadDetails
}