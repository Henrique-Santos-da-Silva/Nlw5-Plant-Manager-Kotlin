package br.com.rocketseat.nextlevelweek.plantmanager.repositories

import br.com.rocketseat.nextlevelweek.plantmanager.api.RetrofitInstance
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantEnvironment
import retrofit2.Response

class PlantManagerRepository {

    suspend fun getHouseRooms(): Response<PlantEnvironment> = RetrofitInstance.api.getHouseRooms()
}