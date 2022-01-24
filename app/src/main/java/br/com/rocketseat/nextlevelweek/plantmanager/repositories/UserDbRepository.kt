package br.com.rocketseat.nextlevelweek.plantmanager.repositories

import androidx.lifecycle.LiveData
import br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.userdb.UserDao
import br.com.rocketseat.nextlevelweek.plantmanager.models.User
import javax.inject.Inject

class UserDbRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: User): Long = userDao.insertUser(user)

    fun getLastUserRecord(): LiveData<User> = userDao.getLastUserRecord()

    suspend fun deleteUser(user: User): Unit = userDao.deleteUser(user)
}