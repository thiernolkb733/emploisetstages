package com.example.emploisetstages.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [
        OffreEntity::class,
        CandidatureEntity::class,
        ModeleLettreEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun offreDao(): OffreDao

    abstract fun candidatureDao(): CandidatureDao

    abstract fun modeleLettreDao(): ModeleLettreDao
}
