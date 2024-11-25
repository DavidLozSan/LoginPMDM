package edu.iesam.loginexam1eval.feature.login.domain

import org.koin.core.annotation.Single

@Single
class RegisterUserUseCase(private val userRepository: UserRepository) {

    fun invoke(user: User): Boolean {
        return userRepository.register(user)
    }
}