package edu.iesam.loginexam1eval.feature.login.presentation

import android.content.Context
import edu.iesam.loginexam1eval.feature.login.data.UserDataRepository
import edu.iesam.loginexam1eval.feature.login.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.feature.login.domain.SignUpUserUseCase

class LoginFactory(private val context: Context) {
    private val loginLocal = LoginXmlLocalDataSource(context)
    private val userDataRepository = UserDataRepository(loginLocal)
    private val signUpUserUseCase = SignUpUserUseCase(userDataRepository)

    fun buildRegisterViewModel(): SignUpViewModel = SignUpViewModel(signUpUserUseCase)
}