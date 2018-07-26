package com.up

import io.ktor.config.ApplicationConfig

internal class ApplicationConfigPropertyProviderImpl(private val applicationConfig: ApplicationConfig):ConfigurationPropertyProvider {
    override fun property(path: String) = applicationConfig.property(path).getString()
}