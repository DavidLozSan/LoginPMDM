package edu.iesam.loginexam1eval.feature.login.domain

data class User(
    val name: String,
    val password: String
)

data class UserCredentials(
    val userName: String,
    val userPassword: String,
    val rememberMe: Boolean
)