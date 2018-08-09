package com.up

internal interface UserProvider {
    fun getUser(id:Int):User?
}