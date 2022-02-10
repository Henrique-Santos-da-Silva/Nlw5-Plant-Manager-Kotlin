package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.dbtypesconverters

import androidx.room.TypeConverter
import org.joda.time.LocalDateTime

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