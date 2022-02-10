package br.com.rocketseat.nextlevelweek.plantmanager.datasource.localdb.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object PlantAppDbMigrations {
    val migration2To3: Migration = object: Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE plant ADD COLUMN notificationId INT NOT NULL DEFAULT 0")
        }
    }
}