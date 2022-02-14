package br.com.rocketseat.nextlevelweek.plantmanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PlantDbViewModel @Inject constructor(private val plantDbRepository: PlantDbRepository) : ViewModel() {

    fun addPlantInFavorites(plant: Plant) {
        viewModelScope.launch {
            plantDbRepository.saveFavoritePlant(plant)
        }
    }

    fun readFavoritesPlants(): LiveData<List<Plant>> = plantDbRepository.getFavoritesPlants()

    fun getLastNotificationId(): Long? = runBlocking {
        plantDbRepository.getLastNotificationId()
    }

    fun deletePlantFavorite(plant: Plant) {
        viewModelScope.launch {
            plantDbRepository.deleteFavoritePlant(plant)
        }
    }
}