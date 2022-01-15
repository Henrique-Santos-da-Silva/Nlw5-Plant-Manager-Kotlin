package br.com.rocketseat.nextlevelweek.plantmanager.models

import com.google.gson.annotations.SerializedName

data class PlantWaterFrequency(
    val times: Int,
    @SerializedName("repeat_every") val repeatEvery: String = PlantKeyValue().title
)
