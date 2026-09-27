package com.odc.emploisetstage.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "modeles_lettre")

data class ModeleLettreEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val titre: String,

    val contenu: String
)

