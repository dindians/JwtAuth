package com.up

import io.ktor.config.ApplicationConfig
import org.kodein.di.Kodein
import org.kodein.di.generic.*

class KodeinBindings {
    fun getKodein(applicationConfig: ApplicationConfig) = Kodein {
        constant(tag = "jwtSigningAlgorithmSecret") with "zAP5MBA4B4Ijz0MZaS48"
        bind<UserProvider>() with provider { UserProviderImpl() }
        bind<ConfigurationPropertyProvider>() with provider {ApplicationConfigPropertyProviderImpl(applicationConfig)}
        bind<JwtPropsProvider>() with provider { JwtPropsProviderImpl(instance()) }
        bind<JwtSigningAlgorithmProvider>() with provider { JwtSigningAlgorithmProviderImpl(this.instance(tag = "jwtSigningAlgorithmSecret")) }
        bind<JwtIssuer>() with provider { JwtIssuerImpl(this.instance<JwtPropsProvider>(), this.instance<JwtSigningAlgorithmProvider>()) }
    }
}
