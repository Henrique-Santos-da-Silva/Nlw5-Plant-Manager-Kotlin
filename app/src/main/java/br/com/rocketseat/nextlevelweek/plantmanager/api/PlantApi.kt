package br.com.rocketseat.nextlevelweek.plantmanager.api

import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantKeyValue
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantWaterFrequency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlantApi {

    @GET("plants_environments")
    suspend fun getHouseRooms(@Query("_sort") sort: String = "title", @Query("_order") order: String = "asc"): Response<List<PlantKeyValue>>

    @GET("plants_water_frequencies")
    suspend fun getPlantsWaterFrequencies(): Response<List<PlantWaterFrequency>>

    @GET("plants")
    suspend fun getAllPlants(@Query("_sort") sort: String = "name", @Query("_order") order: String = "asc", @Query("environments_like") plantEnvironment: String?): Response<List<Plant>>
}