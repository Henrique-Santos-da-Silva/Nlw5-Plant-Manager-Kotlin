package br.com.rocketseat.nextlevelweek.plantmanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantManagerRepository

class PlantViewModelFactory(val plantRepository: PlantManagerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantViewModel(plantRepository) as T
    }
}