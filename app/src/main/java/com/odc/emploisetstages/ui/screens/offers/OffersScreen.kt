package com.example.emploisetstages.ui.screens.offers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class OffreFake(
    val id: Int,
    val titre: String,
    val entreprise: String,
    val ville: String,
    val type: String,
    val secteur: String,
    val description: String,
    val dateLimite: String,
    val contact: String,
    val favori: Boolean = false
)

val fakeOffersPublic = listOf(
    OffreFake(
        id = 1,
        titre = "Développeur Android",
        entreprise = "Orange Guinée",
        ville = "Conakry",
        type = "Emploi",
        secteur = "IT / Télécom",
        description = "Nous recherchons un développeur mobile Android pour rejoindre notre équipe.",
        dateLimite = "30 avr. 2026",
        contact = "recrutement@orange-guinee.com"
    ),
    OffreFake(
        id = 2,
        titre = "Stagiaire UX/UI",
        entreprise = "Digital Center",
        ville = "Conakry",
        type = "Stage",
        secteur = "Marketing",
        description = "Stage de conception d'interfaces utilisateur pour applications mobiles.",
        dateLimite = "15 mai 2026",
        contact = "stages@digitalcenter.gn"
    ),
    OffreFake(
        id = 3,
        titre = "Développeur Backend",
        entreprise = "TechCorp",
        ville = "Kindia",
        type = "Emploi",
        secteur = "IT",
        description = "Poste de développeur backend pour applications d'entreprise.",
        dateLimite = "28 avr. 2026",
        contact = "rh@techcorp.gn"
    )
)

private val filtresType = listOf("Tous", "Emploi", "Stage")

@Composable
fun OffersScreen(
    modifier: Modifier = Modifier,
    onOffreClick: (Int) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var filtreActif by remember { mutableStateOf("Tous") }
    val favoris = remember { mutableStateMapOf<Int, Boolean>() }
    var afficherDialogAjout by remember { mutableStateOf(false) }

    val filteredOffers = fakeOffersPublic.filter { offre ->
        val correspondRecherche = offre.titre.contains(searchQuery, ignoreCase = true) ||
                offre.entreprise.contains(searchQuery, ignoreCase = true)
        val correspondFiltre = filtreActif == "Tous" || offre.type == filtreActif
        correspondRecherche && correspondFiltre
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Button(
                onClick = { afficherDialogAjout = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Ajouter une offre")
            }
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Rechercher une offre...") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                singleLine = true
            )
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filtresType) { filtre ->
                    FilterChip(
                        selected = filtreActif == filtre,
                        onClick = { filtreActif = filtre },
                        label = { Text(filtre) }
                    )
                }
            }
        }

        if (filteredOffers.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aucune offre ne correspond à votre recherche")
                }
            }
        }

        items(filteredOffers) { offre ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOffreClick(offre.id) }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = offre.titre, style = MaterialTheme.typography.titleMedium)
                        Text(text = offre.entreprise, style = MaterialTheme.typography.bodyMedium)
                        Text(text = offre.ville, style = MaterialTheme.typography.bodySmall)
                    }
                    val estFavori = favoris[offre.id] ?: offre.favori
                    IconButton(onClick = { favoris[offre.id] = !estFavori }) {
                        Icon(
                            imageVector = if (estFavori) Icons.Filled.Star else Icons.Filled.StarBorder,
                            contentDescription = "Favori"
                        )
                    }
                }
            }
        }
    }

    if (afficherDialogAjout) {
        DialogAjoutOffre(onDismiss = { afficherDialogAjout = false })
    }
}

@Composable
private fun DialogAjoutOffre(onDismiss: () -> Unit) {
    var titre by remember { mutableStateOf("") }
    var entreprise by remember { mutableStateOf("") }
    var ville by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ajouter une offre") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = titre,
                    onValueChange = { titre = it },
                    label = { Text("Titre") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = entreprise,
                    onValueChange = { entreprise = it },
                    label = { Text("Entreprise") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = ville,
                    onValueChange = { ville = it },
                    label = { Text("Ville") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Ajouter")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Annuler")
            }
        }
    )
}