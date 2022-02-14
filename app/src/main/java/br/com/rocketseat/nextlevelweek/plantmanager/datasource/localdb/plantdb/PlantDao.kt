package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoritePlant(plant: Plant): Long

    @Query("SELECT * FROM plant")
    fun getFavoritesPlants(): LiveData<List<Plant>>

    @Query("SELECT * FROM plant WHERE plantId = :id")
    fun getFavoritePlant(id: Int): LiveData<Plant>

    @Query("SELECT notificationId FROM plant ORDER BY notificationId DESC LIMIT 1")
    suspend fun getLastNotificationId(): Long?

    @Delete
    suspend fun deleteFavoritePlant(plant: Plant)
}