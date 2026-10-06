package com.example.emploisetstages.Repository

import com.example.emploisetstages.Room.CandidatureDao
import com.example.emploisetstages.Room.CandidatureEntity
import com.example.emploisetstages.Room.StatutCandidature
import kotlinx.coroutines.flow.Flow


class CandidatureRepository(
    private val candidatureDao: CandidatureDao
) {

    fun getToutesLesCandidatures(): Flow<List<CandidatureEntity>> {
        return candidatureDao.getToutesLesCandidatures()
    }

    fun getCandidatureParOffre(offreId: Long): Flow<CandidatureEntity?> {
        return candidatureDao.getCandidatureParOffre(offreId)
    }

    fun getCandidaturesParStatut(
        statut: StatutCandidature
    ): Flow<List<CandidatureEntity>> {
        return candidatureDao.getCandidaturesParStatut(statut)
    }

    suspend fun ajouterCandidature(
        candidature: CandidatureEntity
    ): Long {
        return candidatureDao.inserer(candidature)
    }

    suspend fun modifierCandidature(
        candidature: CandidatureEntity
    ) {
        candidatureDao.modifier(candidature)
    }

    suspend fun supprimerCandidature(
        candidature: CandidatureEntity
    ) {
        candidatureDao.supprimer(candidature)
    }
}