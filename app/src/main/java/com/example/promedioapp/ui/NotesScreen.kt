package com.example.promedioapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotesScreen(onCalcularPromedio: (Float) -> Unit) {
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ingreso de notas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nota1,
            onValueChange = {
                nota1 = it
                mensajeError = ""
            },
            label = { Text("Nota 1") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = nota2,
            onValueChange = {
                nota2 = it
                mensajeError = ""
            },
            label = { Text("Nota 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = nota3,
            onValueChange = {
                nota3 = it
                mensajeError = ""
            },
            label = { Text("Nota 3") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                val n1 = nota1.toFloatOrNull()
                val n2 = nota2.toFloatOrNull()
                val n3 = nota3.toFloatOrNull()

                when {
                    n1 == null || n2 == null || n3 == null -> {
                        mensajeError = "Ingresa las 3 notas correctamente"
                    }
                    n1 !in 0f..10f || n2 !in 0f..10f || n3 !in 0f..10f -> {
                        mensajeError = "Las notas deben estar entre 0 y 10"
                    }
                    else -> {
                        mensajeError = ""
                        val promedio = (n1 + n2 + n3) / 3f
                        onCalcularPromedio(promedio)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular promedio")
        }
    }
}