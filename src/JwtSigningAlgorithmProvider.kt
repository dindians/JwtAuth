package com.up

import com.auth0.jwt.algorithms.Algorithm

internal interface JwtSigningAlgorithmProvider {
    fun getAlgorithm(): Algorithm
}