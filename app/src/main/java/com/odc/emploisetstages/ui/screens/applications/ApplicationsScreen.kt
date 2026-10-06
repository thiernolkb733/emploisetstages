package com.example.emploisetstages.ui.screens.applications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.emploisetstages.ui.theme.StatutAEnvoyer
import com.example.emploisetstages.ui.theme.StatutAcceptee
import com.example.emploisetstages.ui.theme.StatutEntretien
import com.example.emploisetstages.ui.theme.StatutEnvoyee
import com.example.emploisetstages.ui.theme.StatutRefusee

data class CandidatureFake(
    val id: Int,
    val offreTitre: String,
    val entreprise: String,
    val statut: String,
    val dateLabel: String
)

val fakeCandidaturesPublic = listOf(
    CandidatureFake(1, "Développeur Mobile Android", "Orange Digital Services", "À envoyer", "Ajoutée le 10 avr. 2025"),
    CandidatureFake(2, "Stage en Marketing Digital", "Global Media", "À envoyer", "Ajoutée le 12 avr. 2025"),
    CandidatureFake(3, "Assistant Comptable", "Cabinet FonDé & Associés", "Envoyée", "Envoyée le 14 avr. 2025"),
    CandidatureFake(4, "Technicien IT", "Tigo Guinée", "Envoyée", "Envoyée le 16 avr. 2025"),
    CandidatureFake(5, "UI/UX Designer", "Digital Africa", "Entretien", "Entretien prévu le 25 avr. 2025"),
    CandidatureFake(6, "Stagiaire Marketing", "Media Plus", "Refusée", "Refusée le 05 avr. 2025")
)

private val ordreStatuts = listOf("À envoyer", "Envoyée", "Entretien", "Acceptée", "Refusée")
private val filtresStatut = listOf("Tous") + ordreStatuts

fun couleurStatut(statut: String): Color = when (statut) {
    "À envoyer" -> StatutAEnvoyer
    "Envoyée" -> StatutEnvoyee
    "Entretien" -> StatutEntretien
    "Acceptée" -> StatutAcceptee
    "Refusée" -> StatutRefusee
    else -> Color.Gray
}

@Composable
fun ApplicationsScreen(modifier: Modifier = Modifier) {
    var filtreActif by remember { mutableStateOf("Tous") }

    val candidaturesFiltrees = if (filtreActif == "Tous") {
        fakeCandidaturesPublic
    } else {
        fakeCandidaturesPublic.filter { it.statut == filtreActif }
    }

    val groupes = ordreStatuts.mapNotNull { statut ->
        val liste = candidaturesFiltrees.filter { it.statut == statut }
        if (liste.isNotEmpty()) statut to liste else null
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filtresStatut) { filtre ->
                    FilterChip(
                        selected = filtreActif == filtre,
                        onClick = { filtreActif = filtre },
                        label = { Text(filtre) }
                    )
                }
            }
        }

        if (groupes.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aucune candidature dans cette catégorie")
                }
            }
        }

        groupes.forEach { (statut, liste) ->
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(couleurStatut(statut), shape = CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(statut, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.width(8.dp))
                    Text("(${liste.size})", style = MaterialTheme.typography.bodySmall)
                }
            }
            items(liste) { candidature ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(candidature.offreTitre, style = MaterialTheme.typography.titleMedium)
                        Text(candidature.entreprise, style = MaterialTheme.typography.bodyMedium)
                        Text(candidature.dateLabel, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}