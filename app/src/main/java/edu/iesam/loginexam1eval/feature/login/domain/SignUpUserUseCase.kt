package edu.iesam.loginexam1eval.feature.login.domain

class SignUpUserUseCase(private val userRepository: UserRepository) {

    fun invoke(user: User): Boolean {
        return userRepository.signUp(user)
    }
}