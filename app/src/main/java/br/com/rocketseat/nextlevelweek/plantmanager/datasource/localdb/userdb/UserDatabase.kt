package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.userdb

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rocketseat.nextlevelweek.plantmanager.models.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}