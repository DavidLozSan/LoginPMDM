package edu.iesam.loginexam1eval.feature.login.domain

interface UserRepository {

    fun signUp(user: User): Boolean
    fun signIn(user: User): Boolean
}