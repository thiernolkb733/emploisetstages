package com.example.emploisetstages.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidatureDao {

    @Query("""
        SELECT * FROM candidatures
        ORDER BY statut
    """)
    fun getToutesLesCandidatures(): Flow<List<CandidatureEntity>>

    @Query("""
        SELECT * FROM candidatures
        WHERE offreId = :offreId
    """)
    fun getCandidatureParOffre(
        offreId: Long
    ): Flow<CandidatureEntity?>

    @Query("""
        SELECT * FROM candidatures
        WHERE statut = :statut
    """)
    fun getCandidaturesParStatut(
        statut: StatutCandidature
    ): Flow<List<CandidatureEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserer(
        candidature: CandidatureEntity
    ): Long

    @Update
    suspend fun modifier(
        candidature: CandidatureEntity
    )

    @Delete
    suspend fun supprimer(
        candidature: CandidatureEntity
    )
}
