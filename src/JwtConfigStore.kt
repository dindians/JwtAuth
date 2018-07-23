package com.up

internal interface JwtConfigStore {
    fun getRealm(): String
    fun getPayload():JwtPayload
}