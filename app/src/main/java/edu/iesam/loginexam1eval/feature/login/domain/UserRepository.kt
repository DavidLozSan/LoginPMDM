package edu.iesam.loginexam1eval.feature.login.domain

interface UserRepository {

    fun register(user: User) : Boolean
}