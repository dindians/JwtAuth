package com.up

import com.auth0.jwt.algorithms.Algorithm

internal class JwtSigningAlgorithmProviderImpl(private val secret:String): JwtSigningAlgorithmProvider
{
    override fun getAlgorithm(): Algorithm = Algorithm.HMAC512(secret)
}