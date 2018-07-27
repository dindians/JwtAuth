package com.up

import io.ktor.config.ApplicationConfig
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.with

object KodeinModules {
    private val globalModule = Kodein.Module(name = "global") {
        bind<UserProvider>() with provider { UserProviderImpl() }
        bind<UserAuthenticator>() with provider { UserProviderImpl() }
    }
    val global = Kodein {
        import(globalModule)
    }

    fun getGlobalWithApplicationConfig(applicationConfig: ApplicationConfig) = Kodein {
        import(globalModule)
    //    extend(global)
        constant(tag = "jwtSigningAlgorithmSecret") with "zAP5MBA4B4Ijz0MZaS48"
        bind<ConfigurationPropertyProvider>() with provider {ApplicationConfigPropertyProviderImpl(applicationConfig)}
        bind<JwtPropsProvider>() with provider { JwtPropsProviderImpl(instance()) }
        bind<JwtSigningAlgorithmProvider>() with provider { JwtSigningAlgorithmProviderImpl(this.instance(tag = "jwtSigningAlgorithmSecret")) }
        bind<JwtIssuer>() with provider { JwtIssuerImpl(this.instance<JwtPropsProvider>(), this.instance<JwtSigningAlgorithmProvider>()) }
    }
}