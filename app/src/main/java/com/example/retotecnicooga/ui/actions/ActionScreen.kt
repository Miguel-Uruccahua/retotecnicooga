package com.example.retotecnicooga.ui.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.retotecnicooga.domain.model.AppDetail
import com.example.retotecnicooga.domain.model.Application

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionScreen(viewModel: ActionsViewModel = hiltViewModel()) {

    val showDialogApp = remember { mutableStateOf(false) }
    val showDialogAppDetail = remember { mutableStateOf(false) }
    val list by viewModel.listState.collectAsStateWithLifecycle(mutableListOf())
    val dataAppDetail by viewModel.dataAppDetail.collectAsState()

    if (showDialogApp.value) {
        dialogNewApp(onConfirm = {
            viewModel.newApplication(it)
            showDialogApp.value = false
        }, onDismiss = { showDialogApp.value = false })
    }

    if (showDialogAppDetail.value) {
        dialogAppDetails(listAppDetail = dataAppDetail,
            onDismiss = { showDialogAppDetail.value = false },
            onUpdateDetail = {
                viewModel.updateAppDetail(it)
                showDialogAppDetail.value = false
            })
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(value = "",
            onValueChange = {},
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            label = { Text(text = "Busqueda") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") })

        Button(
            onClick = { showDialogApp.value = true },
            Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Nueva Aplicación")
            Spacer(modifier = Modifier.size(4.dp))
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
        }

        listApplication(
            list,
            onDelete = { viewModel.deleteApplication(it) },
            onInfo = { viewModel.getAppDetail(it)
                showDialogAppDetail.value = true },
            onAdd = {viewModel.newAppDetail(it)}
        )

    }
}

@Composable
fun listApplication(
    listApplication: List<Application>,
    onDelete: (Application) -> Unit,
    onInfo: (Application) -> Unit,
    onAdd: (AppDetail) ->Unit
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(listApplication, key = { it.id }) { item ->
            AccessItem(item, onDelete = { onDelete(item) }, onInfo = { onInfo(item) }, onAdd = { onAdd(it)} )
        }
    }
}

@Composable
fun AccessItem(
    item: Application,
    onDelete: () -> Unit,
    onInfo: () -> Unit,
    onAdd: (AppDetail) -> Unit
) {
    val showDialogAppDetail = remember { mutableStateOf(false) }
    if (showDialogAppDetail.value) {
        dialogNewAppDetail(detail = null,
            id = item.id,
            onDismiss = { showDialogAppDetail.value = false },
            onSave = {
                showDialogAppDetail.value = false
                onAdd(it)
            })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.small)
            .background(Color(0xFFD7DDE2))
            .border(width = 2.dp, color = Color(0xFF010E3C))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                IconButtonItem(imageVector = Icons.Default.Info, "Detalles") { onInfo() }
                IconButtonItem(
                    imageVector = Icons.Default.AddCircle, "Agregar"
                ) { showDialogAppDetail.value = true }
                IconButtonItem(imageVector = Icons.Default.Delete, "Borrar") { onDelete() }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column() {
                Text(text = "Nombre: ${item.name}", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "Tipo de Aplicación: ${item.type}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(text = "Estado: ${item.state}", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "Compatibilidad: ${item.minCompatibility} a ${item.maxCompatibility}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun IconButtonItem(
    imageVector: ImageVector,
    text: String,
    onclick: () -> Unit)
{
    IconButton(onClick = { onclick() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = imageVector, contentDescription = null
            )
            Text(text = text, fontSize = 8.sp)
        }
    }
}