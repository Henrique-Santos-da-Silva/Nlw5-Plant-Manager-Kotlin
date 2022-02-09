package br.com.rocketseat.nextlevelweek.plantmanager.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "plant")
data class Plant(
    val id: Int,
    @PrimaryKey(autoGenerate = true)
    var plantId: Long? = null,
    val name: String,
    val about: String,
    @SerializedName("water_tips") val waterTips: String,
    val photo: String,
    val environments: List<String>,
    val frequency: PlantWaterFrequency,

    var timeToWater: org.joda.time.LocalDateTime,
    var notificationId: Long
) : Serializable