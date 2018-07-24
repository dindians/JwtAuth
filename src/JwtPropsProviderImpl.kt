package com.up

import io.ktor.config.ApplicationConfig

private const val jwtPrefix = "ktor.jwt"
private const val jwtPayloadPrefix = "$jwtPrefix.payload"

internal class JwtPropsProviderImpl(private val applicationConfig: ApplicationConfig): JwtPropsProvider{
    override fun getRealm() = applicationConfig.property("$jwtPrefix.realm").getString()

    override fun getPayload()= JwtPayload(
            applicationConfig.property("$jwtPayloadPrefix.issuer").getString(),
            applicationConfig.property("$jwtPayloadPrefix.subject").getString(),
            applicationConfig.property("$jwtPayloadPrefix.audience").getString()
    )

    override fun getValidityInMs() = applicationConfig.property("$jwtPrefix.validityInMs").getString().toInt()
}