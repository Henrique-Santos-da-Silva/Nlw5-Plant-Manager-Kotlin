package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.dbtypesconverters.EnvironmentDbConverter
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.dbtypesconverters.TimeToWaterDbConverter
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.dbtypesconverters.WaterFrequencyDbConverters
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant

@Database(entities = [Plant::class], version = 3)
@TypeConverters(value = [WaterFrequencyDbConverters::class, EnvironmentDbConverter::class, TimeToWaterDbConverter::class])
abstract class PlantDatabase: RoomDatabase() {

    abstract fun plantDao(): PlantDao
}