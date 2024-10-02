package com.example.retotecnicooga.ui.actions

import android.util.Log
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
import androidx.compose.runtime.derivedStateOf
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
import com.example.retotecnicooga.domain.model.AppDetail
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dialogNewAppDetail(detail: AppDetail?, onDismiss:()->Unit, onSave:(AppDetail)->Unit,id:Int?){

    var titleContent: String by remember { mutableStateOf(detail?.title ?: "") }
    var descriptionContent: String by remember { mutableStateOf(detail?.description ?: "") }
    var priorityContent: String by remember { mutableStateOf(detail?.priority ?: "") }
    var stateContent: String by remember { mutableStateOf(detail?.state ?: "") }
    var assignedToContent: String by remember { mutableStateOf(detail?.assignedTo ?: "") }
    var dateFinishContent: String by remember { mutableStateOf(detail?.dateFinish ?: "") }

    val priority = listOf("Critica", "Alta", "Media","Baja")
    val state = listOf("En Revisión", "Sin Asignar", "En Desarrollo", "Cerrado")
    var showDateTime by remember { mutableStateOf(false) }
    val dateDialogState = rememberMaterialDialogState()

    if (showDateTime) {
        DialogDateTimePicker(
            onDismiss = { showDateTime = false },
            onConfirm = { dateFinishContent=it },
            dialogState = dateDialogState
        )
    }

    Dialog(
        onDismissRequest = { onDismiss()  },
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
                    value = titleContent,
                    onValueChange = { titleContent = it },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Titulo:") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = ""
                        )
                    }
                )

                OutlinedTextField(
                    value = descriptionContent,
                    onValueChange = { descriptionContent = it },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Descripción:") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = ""
                        )
                    }
                )

                OutlinedTextField(
                    value = assignedToContent,
                    onValueChange = {  assignedToContent = it },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Asignado A:") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = ""
                        )
                    }
                )

                typeAppDetailMenu("Estado", state) { stateContent = it }
                typeAppDetailMenu("Prioridad", priority) { priorityContent = it }


                Button(onClick = {
                    dateDialogState.show()
                    showDateTime = true}) {
                    Text(text = "Fecha Cierre: $dateFinishContent" )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {onDismiss()},
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
                        onClick = { onSave(AppDetail(
                            title = titleContent,
                            description = descriptionContent,
                            priority = priorityContent,
                            state = stateContent,
                            assignedTo = assignedToContent,
                            dateCreated = "",
                            dateFinish=dateFinishContent,
                            id = detail?.id ?:0 ,
                            idApplication = id ?: detail?.idApplication ?:0)
                        ) },
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
fun typeAppDetailMenu(initialText: String, list: List<String>, selectedItem: (String) -> Unit) {
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

@Composable
fun DialogDateTimePicker(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    dialogState: MaterialDialogState
) {
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("yyyyMMdd")
                .format(pickedDate)
        }
    }

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(text = "Ok") {
                onConfirm(formattedDate)
            }
            negativeButton(text = "Cancel"){
                dialogState.hide()
                onDismiss()
            }
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            pickedDate = it
        }
    }
}


