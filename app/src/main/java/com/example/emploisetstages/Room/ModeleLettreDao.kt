package com.odc.emploisetstage.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ModeleLettreDao {

    @Query("""
        SELECT * FROM modeles_lettre
        ORDER BY titre ASC
    """)
    fun getTousLesModeles(): Flow<List<ModeleLettreEntity>>

    @Query("""
        SELECT * FROM modeles_lettre
        WHERE id = :id
    """)
    fun getModeleById(id: Long): Flow<ModeleLettreEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserer(
        modele: ModeleLettreEntity
    ): Long

    @Update
    suspend fun modifier(
        modele: ModeleLettreEntity
    )

    @Delete
    suspend fun supprimer(
        modele: ModeleLettreEntity
    )
}
