package br.com.rocketseat.nextlevelweek.plantmanager.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantKeyValue
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantManagerRepository
import br.com.rocketseat.nextlevelweek.plantmanager.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(private val plantRepository: PlantManagerRepository): ViewModel() {

    private val _listHomeRooms = MutableLiveData<Resource<List<PlantKeyValue>>>()

     val listPlants = MutableLiveData<Response<List<Plant>>>()

    val listHomeRooms: MutableLiveData<Resource<List<PlantKeyValue>>> get() = _listHomeRooms

    init {
        getPlantsEnvironment()
    }

    private fun getPlantsEnvironment() {
        viewModelScope.launch {
            _listHomeRooms.postValue(Resource.Loading())
            val plantsEnvironment: Response<List<PlantKeyValue>> = plantRepository.getHouseRooms()
            _listHomeRooms.value = handlePlantsEnvironmentResponse(plantsEnvironment)
        }
    }

    fun getPlants() {
        viewModelScope.launch {
            val plantResponse = plantRepository.getPlants()
            listPlants.postValue(plantResponse)
        }
    }

    private fun handlePlantsEnvironmentResponse(response: Response<List<PlantKeyValue>>): Resource<List<PlantKeyValue>> {
        if (response.isSuccessful) {
            response.body()?.let { plantResultResponse ->
              return Resource.Success(plantResultResponse)
            }
        }

        return Resource.Error(response.message())
    }
}