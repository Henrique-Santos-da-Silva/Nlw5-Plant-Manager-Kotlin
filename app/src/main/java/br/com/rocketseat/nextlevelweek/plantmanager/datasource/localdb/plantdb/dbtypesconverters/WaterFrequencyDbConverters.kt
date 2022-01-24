package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.dbtypesconverters

import androidx.room.TypeConverter
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantWaterFrequency
import com.google.gson.Gson

class WaterFrequencyDbConverters {

    @TypeConverter
    fun fromFrequency(frequency: PlantWaterFrequency): String = Gson().toJson(frequency)

    @TypeConverter
    fun toFrequency(stringValue: String): PlantWaterFrequency = Gson().fromJson(stringValue, PlantWaterFrequency::class.java)
}