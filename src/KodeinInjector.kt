package com.up

import io.ktor.config.ApplicationConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class KodeinInjector() {
    fun getKodeinAware(applicationConfig: ApplicationConfig): KodeinAware = Kodein {
        bind<UserStore>() with provider { UserStoreImpl() }
        bind<JwtConfigStore>() with provider { JwtPayloadStoreImpl(applicationConfig) }
        bind<JwtAssist>() with provider { JwtAssistImpl(this.instance<JwtConfigStore>()) }
    }
}