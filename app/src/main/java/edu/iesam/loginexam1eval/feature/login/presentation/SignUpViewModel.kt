package edu.iesam.loginexam1eval.feature.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.core.domain.ErrorApp
import edu.iesam.loginexam1eval.feature.login.domain.SignUpUserUseCase
import edu.iesam.loginexam1eval.feature.login.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUpViewModel(private val signUpUserUseCase: SignUpUserUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSignUp(user: User) {
        _uiState.value = (UiState(isLoading = true))

        viewModelScope.launch(Dispatchers.IO) {
            val result = signUpUserUseCase.invoke(user)
            _uiState.postValue(
                UiState(
                    isLoading = false,
                    registered = result
                )
            )
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val registered: Boolean? = null
    )
}