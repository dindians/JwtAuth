package com.up

import io.ktor.config.ApplicationConfig
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.with

internal class CoreDependenciesImpl private constructor(applicationConfig:ApplicationConfig): CoreDependencies
{
    private val appKodein = Kodein {
        bind<UserProvider>() with provider { UserProviderImpl() }
        bind<UserAuthenticator>() with provider { UserProviderImpl() }
        constant(tag = "jwtSigningAlgorithmSecret") with "zAP5MBA4B4Ijz0MZaS48"
        bind<JwtConfig>() with provider { JwtConfigImpl(applicationConfig) }
        bind<JwtSigningAlgorithmProvider>() with provider { JwtSigningAlgorithmProviderImpl(this.instance(tag = "jwtSigningAlgorithmSecret")) }
        bind<JwtIssuer>() with provider { JwtIssuerImpl(this.instance<JwtConfig>(), this.instance<JwtSigningAlgorithmProvider>(), this.instance<UserAuthenticator>(), this.instance<UserProvider>()) }
    }

    override val jwtIssuer: JwtIssuer by appKodein.instance()

    internal companion object {
        fun create(applicationConfig:ApplicationConfig):CoreDependenciesImpl = CoreDependenciesImpl(applicationConfig)
    }
}
