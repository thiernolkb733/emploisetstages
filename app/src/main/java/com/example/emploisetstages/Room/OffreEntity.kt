package com.odc.emploisetstage.Room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "offres",
    indices = [
        Index(value = ["ville"]),
        Index(value = ["type"]),
        Index(value = ["secteur"])
    ]
)
data class OffreEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val titre: String,

    val entreprise: String,

    val ville: String,

    val type: String,

    val secteur: String,

    val dateLimite: LocalDate,

    val description: String,

    val contact: String,

    val estFavori: Boolean = false
)

