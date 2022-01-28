package br.com.rocketseat.nextlevelweek.plantmanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.UserDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userDbRepository: UserDbRepository) : ViewModel() {

    fun insertUser(userName: String) {
        viewModelScope.launch {
            userDbRepository.insertUser(USER_DATASTORE_KEY, userName)
        }
    }

    fun getUser(): String? = runBlocking { userDbRepository.getUser(USER_DATASTORE_KEY) }

    companion object {
        private const val USER_DATASTORE_KEY = "user_key"
    }
}