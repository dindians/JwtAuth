package com.up

import io.ktor.config.ApplicationConfig

private const val jwtPrefix = "ktor.jwt"
private const val jwtPayloadPrefix = "$jwtPrefix.payload"

internal class JwtPropsProviderImpl(private val applicationConfig: ApplicationConfig): JwtPropsProvider{
    private fun property(path: String) = applicationConfig.property(path).getString()

    override val realm = property("$jwtPrefix.realm")
    override val validityInSeconds = property("$jwtPrefix.validityInSeconds").toInt()

    override fun getPayloadDetails()= JwtPayloadDetails(
            property("$jwtPayloadPrefix.issuer"),
            property("$jwtPayloadPrefix.subject"),
            property("$jwtPayloadPrefix.audience")
    )
}