package com.odc.emploietstage.Repository

import com.odc.emploietstage.Room.ModeleLettreDao
import com.odc.emploietstage.Room.ModeleLettreEntity
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