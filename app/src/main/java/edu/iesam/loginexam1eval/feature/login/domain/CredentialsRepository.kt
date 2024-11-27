package edu.iesam.loginexam1eval.feature.login.domain

interface CredentialsRepository {

    fun getSavedCredentials(): UserCredentials?
    fun save(credentials: UserCredentials)
    fun clear()
}