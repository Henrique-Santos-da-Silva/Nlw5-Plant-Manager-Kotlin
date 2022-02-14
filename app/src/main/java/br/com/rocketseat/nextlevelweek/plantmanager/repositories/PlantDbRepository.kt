package br.com.rocketseat.nextlevelweek.plantmanager.repositories

import androidx.lifecycle.LiveData
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.plantdb.PlantDao
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import javax.inject.Inject

class PlantDbRepository @Inject constructor(private val plantDao: PlantDao) {

    suspend fun saveFavoritePlant(plant: Plant): Long = plantDao.saveFavoritePlant(plant)

    fun getFavoritesPlants(): LiveData<List<Plant>> = plantDao.getFavoritesPlants()

    fun getFavoritePlant(id: Int): LiveData<Plant> = plantDao.getFavoritePlant(id)

    suspend fun getLastNotificationId(): Long? = plantDao.getLastNotificationId()

    suspend fun deleteFavoritePlant(plant: Plant): Unit = plantDao.deleteFavoritePlant(plant)
}