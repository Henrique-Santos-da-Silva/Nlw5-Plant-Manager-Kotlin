package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.dbtypesconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EnvironmentDbConverter {

    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object: TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toListString(list: List<String?>): String {
        return Gson().toJson(list)
    }
}