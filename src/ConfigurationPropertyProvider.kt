package com.up

interface ConfigurationPropertyProvider {
    fun property(path: String):String
}