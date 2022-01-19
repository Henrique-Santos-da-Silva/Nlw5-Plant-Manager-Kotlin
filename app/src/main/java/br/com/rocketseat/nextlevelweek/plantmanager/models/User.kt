package br.com.rocketseat.nextlevelweek.plantmanager.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val userName: String
) : Serializable
