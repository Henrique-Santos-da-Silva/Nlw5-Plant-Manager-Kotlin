package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.userdb

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.rocketseat.nextlevelweek.plantmanager.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    fun getLastUserRecord(): LiveData<User>

    @Delete
    suspend fun deleteUser(user: User)
}