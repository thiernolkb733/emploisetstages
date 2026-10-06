package com.example.emploisetstages.Repository

import com.example.emploisetstages.Room.ModeleLettreDao
import com.example.emploisetstages.Room.ModeleLettreEntity
import kotlinx.coroutines.flow.Flow


class ModeleLettreRepository(
    private val modeleLettreDao: ModeleLettreDao
) {

    fun getTousLesModeles(): Flow<List<ModeleLettreEntity>> {
        return modeleLettreDao.getTousLesModeles()
    }

    fun getModeleById(id: Long): Flow<ModeleLettreEntity?> {
        return modeleLettreDao.getModeleById(id)
    }

    suspend fun ajouterModele(
        modele: ModeleLettreEntity
    ): Long {
        return modeleLettreDao.inserer(modele)
    }

    suspend fun modifierModele(
        modele: ModeleLettreEntity
    ) {
        modeleLettreDao.modifier(modele)
    }

    suspend fun supprimerModele(
        modele: ModeleLettreEntity
    ) {
        modeleLettreDao.supprimer(modele)
    }
}