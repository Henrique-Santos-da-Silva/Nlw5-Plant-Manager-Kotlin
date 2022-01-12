package br.com.rocketseat.nextlevelweek.plantmanager.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantEnvironment
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantManagerRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PlantViewModel(private val plantRepository: PlantManagerRepository): ViewModel() {

    val listHomeRooms = MutableLiveData<Response<PlantEnvironment>>()

    fun getPlantsEnvironment() {
        viewModelScope.launch {
            val plantsEnvironment: Response<PlantEnvironment> = plantRepository.getHouseRooms()
            listHomeRooms.postValue(plantsEnvironment)
        }
    }
}