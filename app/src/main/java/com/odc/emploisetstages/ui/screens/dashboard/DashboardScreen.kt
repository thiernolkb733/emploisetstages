package com.example.emploisetstages.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.emploisetstages.ui.screens.applications.fakeCandidaturesPublic
import com.example.emploisetstages.ui.screens.offers.fakeOffersPublic

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    val total = fakeCandidaturesPublic.size
    val aEnvoyer = fakeCandidaturesPublic.count { it.statut == "À envoyer" }
    val envoyees = fakeCandidaturesPublic.count { it.statut == "Envoyée" }
    val entretiens = fakeCandidaturesPublic.count { it.statut == "Entretien" }
    val acceptees = fakeCandidaturesPublic.count { it.statut == "Acceptée" }
    val refusees = fakeCandidaturesPublic.count { it.statut == "Refusée" }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total des candidatures", style = MaterialTheme.typography.bodyMedium)
                    Text("$total", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                StatCard("À envoyer", aEnvoyer, Modifier.weight(1f))
                StatCard("Envoyées", envoyees, Modifier.weight(1f))
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                StatCard("Entretiens", entretiens, Modifier.weight(1f))
                StatCard("Acceptées", acceptees, Modifier.weight(1f))
            }
        }
        item {
            StatCard("Refusées", refusees, Modifier.fillMaxWidth())
        }

        item {
            Text(
                "Prochaines échéances",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        items(fakeOffersPublic) { offre ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(offre.entreprise, style = MaterialTheme.typography.bodyMedium)
                    Text("Date limite : ${offre.dateLimite}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Icon(Icons.Filled.Info, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text("Conseil du jour", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "N'oublie pas de faire ta relance pour les offres en attente.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(label: String, valeur: Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, style = MaterialTheme.typography.bodySmall)
            Text("$valeur", style = MaterialTheme.typography.titleLarge)
        }
    }
}