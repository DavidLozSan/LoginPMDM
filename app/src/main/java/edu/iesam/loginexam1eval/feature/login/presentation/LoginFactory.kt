package edu.iesam.loginexam1eval.feature.login.presentation

import android.content.Context
import edu.iesam.loginexam1eval.feature.login.data.CredentialsDataRepository
import edu.iesam.loginexam1eval.feature.login.data.UserDataRepository
import edu.iesam.loginexam1eval.feature.login.data.local.CredentialsXmlLocalDataSource
import edu.iesam.loginexam1eval.feature.login.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.feature.login.domain.CredentialsUserUseCase
import edu.iesam.loginexam1eval.feature.login.domain.DeleteAccountUseCase
import edu.iesam.loginexam1eval.feature.login.domain.SignInUserUseCase
import edu.iesam.loginexam1eval.feature.login.domain.SignUpUserUseCase

class LoginFactory(private val context: Context) {
    private val loginLocal = LoginXmlLocalDataSource(context)
    private val userDataRepository = UserDataRepository(loginLocal)
    private val signUpUserUseCase = SignUpUserUseCase(userDataRepository)
    private val signInUserCase = SignInUserUseCase(userDataRepository)
    private val credentialsLocal = CredentialsXmlLocalDataSource(context)
    private val credentialsDataRepository = CredentialsDataRepository(credentialsLocal)
    private val credentialsUserUseCase = CredentialsUserUseCase(credentialsDataRepository)
    private val deleteAccountUseCase = DeleteAccountUseCase(userDataRepository)

    fun buildSignUpViewModel(): SignUpViewModel = SignUpViewModel(signUpUserUseCase)
    fun buildSignInViewModel(): SignInViewModel =
        SignInViewModel(signInUserCase, credentialsUserUseCase)

    fun buildDeleteAccountViewModel(): DeleteAccountViewModel =
        DeleteAccountViewModel(deleteAccountUseCase)
}