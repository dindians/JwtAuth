package com.up

import io.ktor.config.ApplicationConfig

private const val jwtPrefix = "ktor.jwt"
private const val jwtPayloadPrefix = "$jwtPrefix.payload"

internal class JwtConfigImpl(private val applicationConfig: ApplicationConfig): JwtConfig{
    private fun property(path: String) = applicationConfig.property(path).getString()

    override val realm = property("$jwtPrefix.realm")
    override val validityInSeconds = property("$jwtPrefix.validityInSeconds").toInt()

    override fun getPayloadConfig()= JwtPayloadConfig(
            property("$jwtPayloadPrefix.issuer"),
            property("$jwtPayloadPrefix.subject"),
            property("$jwtPayloadPrefix.audience")
    )
}