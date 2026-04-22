package com.example.promedioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.promedioapp.ui.LoginScreen
import com.example.promedioapp.ui.NotesScreen
import com.example.promedioapp.ui.ResultScreen
import com.example.promedioapp.ui.WelcomeScreen
import com.example.promedioapp.ui.theme.PromedioAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PromedioAppTheme {
                var pantallaActual by rememberSaveable { mutableStateOf("login") }
                var nombreUsuario by rememberSaveable { mutableStateOf("") }
                var promedioFinal by rememberSaveable { mutableFloatStateOf(0f) }

                when (pantallaActual) {
                    "login" -> LoginScreen(
                        onLoginSuccess = { nombre ->
                            nombreUsuario = nombre
                            pantallaActual = "welcome"
                        }
                    )

                    "welcome" -> WelcomeScreen(
                        nombreUsuario = nombreUsuario,
                        onContinuar = {
                            pantallaActual = "notes"
                        }
                    )

                    "notes" -> NotesScreen(
                        onCalcularPromedio = { promedio ->
                            promedioFinal = promedio
                            pantallaActual = "result"
                        }
                    )

                    "result" -> ResultScreen(
                        promedio = promedioFinal,
                        onVolverNotas = {
                            pantallaActual = "notes"
                        },
                        onCerrarSesion = {
                            nombreUsuario = ""
                            promedioFinal = 0f
                            pantallaActual = "login"
                        }
                    )
                }
            }
        }
    }
}