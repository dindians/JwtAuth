package com.up

interface ApplicationDependencies {
    val jwtIssuer: JwtIssuer
    val userProvider: UserProvider
    val jwtPropsProvider: JwtPropsProvider
    val userAuthenticator: UserAuthenticator

}