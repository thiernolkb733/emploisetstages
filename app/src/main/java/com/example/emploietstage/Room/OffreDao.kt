package com.odc.emploietstage.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OffreDao {

    @Query("SELECT * FROM offres ORDER BY dateLimite ASC")
    fun getToutesLesOffres(): Flow<List<OffreEntity>>

    @Query("SELECT * FROM offres WHERE id = :id")
    fun getOffreById(id: Long): Flow<OffreEntity?>

    @Query("""
        SELECT * FROM offres
        WHERE ville = :ville
        ORDER BY dateLimite ASC
    """)
    fun getOffresParVille(ville: String): Flow<List<OffreEntity>>

    @Query("""
        SELECT * FROM offres
        WHERE type = :type
        ORDER BY dateLimite ASC
    """)
    fun getOffresParType(type: String): Flow<List<OffreEntity>>

    @Query("""
        SELECT * FROM offres
        WHERE secteur = :secteur
        ORDER BY dateLimite ASC
    """)
    fun getOffresParSecteur(secteur: String): Flow<List<OffreEntity>>

    @Query("""
        SELECT * FROM offres
        WHERE estFavori = 1
        ORDER BY dateLimite ASC
    """)
    fun getFavoris(): Flow<List<OffreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserer(offre: OffreEntity): Long

    @Update
    suspend fun modifier(offre: OffreEntity)

    @Delete
    suspend fun supprimer(offre: OffreEntity)

    @Query("""
        UPDATE offres
        SET estFavori = NOT estFavori
        WHERE id = :offreId
    """)
    suspend fun changerFavori(offreId: Long)
}
