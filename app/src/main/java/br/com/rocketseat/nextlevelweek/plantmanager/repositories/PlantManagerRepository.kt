package br.com.rocketseat.nextlevelweek.plantmanager.repositories

import br.com.rocketseat.nextlevelweek.plantmanager.api.PlantApi
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantKeyValue
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantWaterFrequency
import retrofit2.Response
import javax.inject.Inject

class PlantManagerRepository @Inject constructor(private val plantApi: PlantApi) {

    suspend fun getHouseRooms(): Response<List<PlantKeyValue>> = plantApi.getHouseRooms()

    suspend fun getPlants(plantEnvironment: String?): Response<List<Plant>> = plantApi.getAllPlants(plantEnvironment = plantEnvironment)
}