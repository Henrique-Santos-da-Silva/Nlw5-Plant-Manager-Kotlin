package br.com.rocketseat.nextlevelweek.plantmanager.api

import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantEnvironment
import retrofit2.Response
import retrofit2.http.GET

interface PlantApi {

    @GET("plants_environments")
    suspend fun getHouseRooms(): Response<PlantEnvironment>
}