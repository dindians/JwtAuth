package com.up

internal interface JwtPropsProvider {
    fun getRealm(): String
    fun getPayload():JwtPayload
    fun getValidityInMs(): Int
}