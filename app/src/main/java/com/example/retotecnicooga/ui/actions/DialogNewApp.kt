package com.example.retotecnicooga.ui.actions

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.retotecnicooga.domain.model.Application

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dialogNewApp(
    onDismiss: () -> Unit,
    onConfirm: (item: Application) -> Unit,
) {

    var name: String by remember { mutableStateOf("") }
    var type: String by remember { mutableStateOf("") }
    var minSdk: String by remember { mutableStateOf("") }
    var maxSdk: String by remember { mutableStateOf("") }
    var stateValue: String by remember { mutableStateOf("") }
    val items = listOf(
        "Acceso",
        "Alimentación",
        "Soporte",
        "Dotación",
        "Comunicación",
        "Social",
        "Transporte",
        "Salud"
    )
    val sdk = listOf(
        "Android 8",
        "Android 9",
        "Android 10",
        "Android 11",
        "Android 12",
        "Android 13",
        "Android 14"
    )
    val state = listOf("Cotización", "Desarrollo", "Finalizado")

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(2.dp, color = Color(0xFF163C5D), shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Nombre") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = ""
                        )
                    }
                )

                typeAppMenu("Tipo de Aplicación", items) { type = it }
                typeAppMenu("versión minima", sdk) { minSdk = it }
                typeAppMenu("versión maxima", sdk) { maxSdk = it }
                typeAppMenu("Selecciona un Estado", state) { stateValue = it }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF163C5D))
                    ) {
                        Text(
                            text = "Cancelar",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )

                    }
                    Button(
                        onClick = {
                            onConfirm(
                                Application(
                                    name = name,
                                    type = type,
                                    minCompatibility = minSdk,
                                    maxCompatibility = maxSdk,
                                    state = stateValue,
                                    isUpToolsFollow = false,
                                    id = 0
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF163C5D))
                    ) {
                        Text(
                            text = "Confirmar",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun typeAppMenu(initialText: String, list: List<String>, selectedItem: (String) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    var selectedText: String by remember { mutableStateOf("Ninguno") }
    Column {
        Button(onClick = { expanded.value = !expanded.value }) {
            Text(text = "$initialText: $selectedText")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
        ) {
            list.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        selectedItem(item)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

