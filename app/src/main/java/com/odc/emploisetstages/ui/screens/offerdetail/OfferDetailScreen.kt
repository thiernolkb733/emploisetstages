package com.example.emploisetstages.ui.screens.offerdetail

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.emploisetstages.ui.screens.offers.fakeOffersPublic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferDetailScreen(
    offerId: Int,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val offre = fakeOffersPublic.find { it.id == offerId }
    var estFavori by remember { mutableStateOf(false) }
    var afficherLettre by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    IconButton(onClick = { estFavori = !estFavori }) {
                        Icon(
                            imageVector = if (estFavori) Icons.Filled.Star else Icons.Filled.StarBorder,
                            contentDescription = "Favori"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (offre == null) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                Text("Offre introuvable")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(text = offre.titre, style = MaterialTheme.typography.headlineSmall)
            Text(text = offre.entreprise, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = {}, label = { Text(offre.type) })
                AssistChip(onClick = {}, label = { Text("Date limite : ${offre.dateLimite}") })
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description", style = MaterialTheme.typography.titleMedium)
            Text(text = offre.description, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Contact", style = MaterialTheme.typography.titleMedium)
            Text(text = offre.contact, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text("Postuler")
            }

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { afficherLettre = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Voir la lettre de candidature")
            }
        }

        if (afficherLettre) {
            DialogLettre(
                titre = offre.titre,
                entreprise = offre.entreprise,
                onDismiss = { afficherLettre = false }
            )
        }
    }
}

@Composable
private fun DialogLettre(titre: String, entreprise: String, onDismiss: () -> Unit) {
    val context = LocalContext.current

    val contenuLettre = """
        Madame, Monsieur,

        Je vous adresse ma candidature pour le poste de $titre au sein de $entreprise.

        Fort(e) de mes compétences et de ma motivation, je suis convaincu(e) de pouvoir apporter une réelle valeur ajoutée à votre équipe.

        Je reste à votre disposition pour un entretien à votre convenance.

        Cordialement,
    """.trimIndent()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Lettre de candidature") },
        text = { Text(contenuLettre) },
        confirmButton = {
            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, contenuLettre)
                }
                context.startActivity(Intent.createChooser(intent, "Partager la lettre"))
            }) {
                Icon(Icons.Filled.Share, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Partager")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Fermer")
            }
        }
    )
}