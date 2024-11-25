package edu.iesam.loginexam1eval.feature.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.login.domain.RegisterUserUseCase
import edu.iesam.loginexam1eval.feature.login.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RegisterViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<Uistate>()
    val uiState: LiveData<Uistate> = _uiState

    fun loadRegister(user: User) {
        _uiState.value = (Uistate(isLoding = true))

        viewModelScope.launch(Dispatchers.IO) {
            val result = registerUserUseCase.invoke(user)
            _uiState.postValue(
                Uistate(
                    isLoading = false,
                    registered = result
                )
            )
        }
    }
}

data class Uistate(
    val isLoading: Boolean = false,
    val error: Error? = null,
    val registered: Boolean? = null
)