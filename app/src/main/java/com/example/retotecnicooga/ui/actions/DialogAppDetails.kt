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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.retotecnicooga.domain.model.AppDetail


@Composable
fun dialogAppDetails(listAppDetail: List<AppDetail>,onDismiss: () -> Unit, onUpdateDetail: (AppDetail) -> Unit) {

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
            if(listAppDetail.isNullOrEmpty()){
                Text(text = "Sin Detalles Agregados")
            }else{
                listAppDetail(listAppDetail){ onUpdateDetail(it) }
            }
        }
    }
}

@Composable
fun listAppDetail(listApplication: List<AppDetail>, onUpdateDetail: (AppDetail) -> Unit) {

    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(listApplication, key = { it.id }) { item ->
            detailItem(item){ onUpdateDetail(it) }
        }
    }
}

@Composable
fun detailItem(item: AppDetail,onUpdateDetail:(AppDetail)->Unit) {

    val showDialogAppDetail = remember { mutableStateOf(false) }

    if (showDialogAppDetail.value){
        dialogNewAppDetail(
            detail = item,
            id = null,
            onDismiss = {showDialogAppDetail.value=false},
            onSave = {onUpdateDetail(it)})
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
            Column {
                IconButtonItem(imageVector = Icons.Default.Edit,"Editar"){showDialogAppDetail.value=true}
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column() {
                Text(text = "Titulo: ${item.title}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Descripción: ${item.description}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Prioridad: ${item.priority}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Estado: ${item.state}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Asignado A: ${item.assignedTo}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Fecha Creación: ${item.dateCreated}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Fecha de Entrega o Cierre: ${item.dateFinish}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}