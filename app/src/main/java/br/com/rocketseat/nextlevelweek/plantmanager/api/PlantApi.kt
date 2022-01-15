package br.com.rocketseat.nextlevelweek.plantmanager.api

import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantKeyValue
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantWaterFrequency
import retrofit2.Response
import retrofit2.http.GET

interface PlantApi {

    @GET("plants_environments")
    suspend fun getHouseRooms(): Response<List<PlantKeyValue>>

    @GET("plants_water_frequencies")
    suspend fun getPlantsWaterFrequencies(): Response<List<PlantWaterFrequency>>

    @GET("plants")
    suspend fun getAllPlants(): Response<List<Plant>>
}