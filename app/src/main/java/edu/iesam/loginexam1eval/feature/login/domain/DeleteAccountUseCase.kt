package edu.iesam.loginexam1eval.feature.login.domain

class DeleteAccountUseCase(private val userRepository: UserRepository) {

    fun invoke(user: User): Boolean {
        return userRepository.deleteAccount(user)
    }
}