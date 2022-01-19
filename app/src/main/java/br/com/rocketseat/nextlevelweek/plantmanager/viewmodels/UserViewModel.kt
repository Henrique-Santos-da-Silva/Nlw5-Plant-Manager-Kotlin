package br.com.rocketseat.nextlevelweek.plantmanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rocketseat.nextlevelweek.plantmanager.models.User
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.UserDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userDbRepository: UserDbRepository): ViewModel() {

    fun insertUser(user: User) {
        viewModelScope.launch {
            userDbRepository.insertUser(user)
        }
    }

    fun getLastUserRecord(): LiveData<User> = userDbRepository.getLastUserRecord()

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDbRepository.deleteUser(user)
        }
    }
}