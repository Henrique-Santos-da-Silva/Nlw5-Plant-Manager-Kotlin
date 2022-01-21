package br.com.rocketseat.nextlevelweek.plantmanager.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Plant(
    val id: Int,
    val name: String,
    val about: String,
    @SerializedName("water_tips") val waterTips: String,
    val photo: String,
    val environments: List<String>,
    val frequency: PlantWaterFrequency
) : Serializable
