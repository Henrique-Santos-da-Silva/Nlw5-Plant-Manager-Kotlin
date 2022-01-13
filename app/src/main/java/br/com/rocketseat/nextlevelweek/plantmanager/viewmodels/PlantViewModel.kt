package br.com.rocketseat.nextlevelweek.plantmanager.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantEnvironment
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantManagerRepository
import br.com.rocketseat.nextlevelweek.plantmanager.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PlantViewModel(val plantRepository: PlantManagerRepository): ViewModel() {

    val listHomeRooms = MutableLiveData<Resource<PlantEnvironment>>()

    fun getPlantsEnvironment() {
        viewModelScope.launch {
            listHomeRooms.postValue(Resource.Loading())
            val plantsEnvironment: Response<PlantEnvironment> = plantRepository.getHouseRooms()
            listHomeRooms.postValue(handlePlantsEnvironmentResponse(plantsEnvironment))
        }
    }

    private fun handlePlantsEnvironmentResponse(response: Response<PlantEnvironment>): Resource<PlantEnvironment> {
        if (response.isSuccessful) {
            response.body()?.let { plantResultResponse ->
              return Resource.Success(plantResultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}