package edu.iesam.loginexam1eval.feature.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.login.domain.CredentialsUserUseCase
import edu.iesam.loginexam1eval.feature.login.domain.SignInUserUseCase
import edu.iesam.loginexam1eval.feature.login.domain.User
import edu.iesam.loginexam1eval.feature.login.domain.UserCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUserUseCase: SignInUserUseCase,
    private val credentialsUserUseCase: CredentialsUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private val _savedCredentials = MutableLiveData<User?>()
    val savedCredentials: LiveData<User?> get() = _savedCredentials

    fun loadSavedCredentials() {
        viewModelScope.launch {
            val credentials = credentialsUserUseCase.getSavedCredentials()
            _savedCredentials.value = credentials?.let { userCredentials ->
                User(userCredentials.userName, userCredentials.userPassword)
            }
        }
    }

    fun loadSignIn(user: User, rememberMe: Boolean) {
        _uiState.value = (UiState(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            val result = signInUserUseCase.invoke(user)
            _uiState.postValue(
                UiState(
                    isLoading = false,
                    registered = result
                )
            )

            if (result && rememberMe) {
                credentialsUserUseCase.save(UserCredentials(user.name, user.password, true))
            } else if (!rememberMe) {
                credentialsUserUseCase.clear()
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val registered: Boolean? = null
    )
}