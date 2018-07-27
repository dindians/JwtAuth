package com.up

import io.ktor.config.ApplicationConfig
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.with

internal class ApplicationDependenciesImpl private constructor(applicationConfig:ApplicationConfig): ApplicationDependencies
{
    private val appKodein = Kodein {
        bind<UserProvider>() with provider { UserProviderImpl() }
        bind<UserAuthenticator>() with provider { UserProviderImpl() }
        constant(tag = "jwtSigningAlgorithmSecret") with "zAP5MBA4B4Ijz0MZaS48"
        bind<JwtPropsProvider>() with provider { JwtPropsProviderImpl(applicationConfig) }
        bind<JwtSigningAlgorithmProvider>() with provider { JwtSigningAlgorithmProviderImpl(this.instance(tag = "jwtSigningAlgorithmSecret")) }
        bind<JwtIssuer>() with provider { JwtIssuerImpl(this.instance<JwtPropsProvider>(), this.instance<JwtSigningAlgorithmProvider>()) }
    }

    override val jwtIssuer: JwtIssuer by appKodein.instance()
    override val userProvider: UserProvider by appKodein.instance()
    override val jwtPropsProvider: JwtPropsProvider by appKodein.instance()
    override val userAuthenticator: UserAuthenticator by appKodein.instance()

    internal companion object {
        fun create(applicationConfig:ApplicationConfig):ApplicationDependenciesImpl = ApplicationDependenciesImpl(applicationConfig)
    }
}
