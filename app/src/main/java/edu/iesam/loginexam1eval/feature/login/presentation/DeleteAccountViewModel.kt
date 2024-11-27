package edu.iesam.loginexam1eval.feature.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.feature.login.domain.DeleteAccountUseCase
import edu.iesam.loginexam1eval.feature.login.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteAccountViewModel(
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadDeleteAccount(user: User) {
        _uiState.value = (UiState(isLoading = true))

        viewModelScope.launch(Dispatchers.IO) {
            val result = deleteAccountUseCase.invoke(user)
            _uiState.postValue(
                UiState(
                    isLoading = false,
                    deleted = result
                )
            )
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val deleted: Boolean? = null
    )
}