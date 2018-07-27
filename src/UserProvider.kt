package com.up

interface UserProvider {
    fun getUser(id:Int):User?
}