package com.example.emploisetstages.Room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "candidatures",
    foreignKeys = [
        ForeignKey(
            entity = OffreEntity::class,
            parentColumns = ["id"],
            childColumns = ["offreId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["offreId"]),
        Index(value = ["statut"])
    ]
)
data class CandidatureEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val offreId: Long,

    val statut: StatutCandidature,

    val dateEnvoi: LocalDate? = null,

    val notes: String? = null
)

