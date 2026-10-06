package com.example.emploisetstages.Repository

import com.example.emploisetstages.Room.OffreDao
import com.example.emploisetstages.Room.OffreEntity
import kotlinx.coroutines.flow.Flow


class OffreRepository(
    private val offreDao: OffreDao
) {

    fun getToutesLesOffres(): Flow<List<OffreEntity>> {
        return offreDao.getToutesLesOffres()
    }

    fun getOffreById(id: Long): Flow<OffreEntity?> {
        return offreDao.getOffreById(id)
    }

    suspend fun ajouterOffre(offre: OffreEntity) {
        offreDao.inserer(offre)
    }

    suspend fun modifierOffre(offre: OffreEntity) {
        offreDao.modifier(offre)
    }

    suspend fun supprimerOffre(offre: OffreEntity) {
        offreDao.supprimer(offre)
    }

    suspend fun changerFavori(id: Long) {
        offreDao.changerFavori(id)
    }
}