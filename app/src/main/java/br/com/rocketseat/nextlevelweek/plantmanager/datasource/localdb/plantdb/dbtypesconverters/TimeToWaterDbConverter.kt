package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.dbtypesconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.joda.time.LocalDateTime
import java.lang.reflect.Type

class TimeToWaterDbConverter {
    
    @TypeConverter
    fun toLocalTime(timeToWater: LocalDateTime?): String? {
        return timeToWater?.toString()
    }

    @TypeConverter
    fun fromString(stringValue: String?): LocalDateTime? {
        return stringValue?.let { LocalDateTime(it) }
    }
}