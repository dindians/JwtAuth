package com.up

import io.ktor.auth.Principal

internal data class User(
        val id: Int,
        val name: String,
        val countries: List<String>
) : Principal