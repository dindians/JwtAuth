package com.up

import io.ktor.config.ApplicationConfig

private const val jwtPrefix = "ktor.jwt"
private const val jwtPayloadPrefix = "$jwtPrefix.payload"

internal class JwtPropsProviderImpl(private val applicationConfig: ApplicationConfig): JwtPropsProvider{
    override val realm = applicationConfig.property("$jwtPrefix.realm").getString()
    override val validityInSeconds = applicationConfig.property("$jwtPrefix.validityInSeconds").getString().toInt()

    override fun getPayload()= JwtPayload(
            applicationConfig.property("$jwtPayloadPrefix.issuer").getString(),
            applicationConfig.property("$jwtPayloadPrefix.subject").getString(),
            applicationConfig.property("$jwtPayloadPrefix.audience").getString()
    )
}