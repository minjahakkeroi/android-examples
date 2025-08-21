package com.example.attackerapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign

/*
 * Attacker App - Attacker App Screen
 * - UI of the Attacker App using Material 3 design components
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttackerAppScreen(
    stolenTokenMessage: String,
    onStealClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Attacker App",
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("This app tries to access a sensitive activity in another app.")

            Button(onClick = onStealClick) {
                Text("Try to Steal Token")
            }

            Text(
                text = "Stolen Token: $stolenTokenMessage",
                textAlign = TextAlign.Start
            )
        }
    }
}