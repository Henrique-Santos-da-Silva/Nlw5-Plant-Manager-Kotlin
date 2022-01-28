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
class PlantViewModel @Inject constructor(private val plantRepository: PlantManagerRepository) : ViewModel() {

    private val _listHomeRooms = MutableLiveData<Resource<List<PlantKeyValue>>>()
    val listHomeRooms: MutableLiveData<Resource<List<PlantKeyValue>>> get() = _listHomeRooms

    val listPlants = MutableLiveData<Resource<List<Plant>>>()


    init {
        getPlantsEnvironment()
        getPlants(null)
    }

    private fun getPlantsEnvironment() {
        viewModelScope.launch {
            _listHomeRooms.postValue(Resource.Loading())
            val plantsEnvironment: Response<List<PlantKeyValue>> = plantRepository.getHouseRooms()
            _listHomeRooms.value = handlePlantsEnvironmentResponse(plantsEnvironment)
        }
    }

    fun getPlants(plantEnvironment: String?) {
        viewModelScope.launch {
            listPlants.postValue(Resource.Loading())
            val plantResponse = plantRepository.getPlants(plantEnvironment)
            listPlants.postValue(handlePlantsResponse(plantResponse))
        }
    }

    private fun handlePlantsResponse(response: Response<List<Plant>>): Resource<List<Plant>> {
        if (response.isSuccessful) {
            response.body()?.let { plant ->
                return Resource.Success(plant)
            }
        }

        return Resource.Error(response.message())
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