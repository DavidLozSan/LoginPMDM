package edu.iesam.loginexam1eval.feature.login.domain

class SignInUserUseCase(private val userRepository: UserRepository) {

    fun invoke(user: User): Boolean {
        return userRepository.signIn(user)
    }
}