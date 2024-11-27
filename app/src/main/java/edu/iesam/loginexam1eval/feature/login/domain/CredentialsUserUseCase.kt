package edu.iesam.loginexam1eval.feature.login.domain

class CredentialsUserUseCase(private val credentialsRepository: CredentialsRepository) {

    fun getSavedCredentials(): UserCredentials? {
        return credentialsRepository.getSavedCredentials()
    }

    fun save(credentials: UserCredentials) {
        credentialsRepository.save(credentials)
    }

    fun clear() {
        credentialsRepository.clear()
    }
}