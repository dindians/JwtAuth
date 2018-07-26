package com.up

private const val jwtPrefix = "ktor.jwt"
private const val jwtPayloadPrefix = "$jwtPrefix.payload"

internal class JwtPropsProviderImpl(private val configurationPropertyProvider:ConfigurationPropertyProvider): JwtPropsProvider{
    override val realm = configurationPropertyProvider.property("$jwtPrefix.realm")
    override val validityInSeconds = configurationPropertyProvider.property("$jwtPrefix.validityInSeconds").toInt()

    override fun getPayload()= JwtPayload(
            configurationPropertyProvider.property("$jwtPayloadPrefix.issuer"),
            configurationPropertyProvider.property("$jwtPayloadPrefix.subject"),
            configurationPropertyProvider.property("$jwtPayloadPrefix.audience")
    )
}