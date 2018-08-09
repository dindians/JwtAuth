package com.up

interface ApplicationDependencies {
    val jwtIssuer: JwtIssuer
    val userProvider: UserProvider
    val userAuthenticator: UserAuthenticator

}