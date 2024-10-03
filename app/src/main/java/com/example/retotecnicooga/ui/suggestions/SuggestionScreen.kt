package com.example.retotecnicooga.ui.suggestions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.retotecnicooga.domain.model.Suggestion

@Composable
fun SuggestionScreen(viewModel: SuggestionsViewModel = hiltViewModel()) {

    val listLowerSuggestion by viewModel.listLowerSuggestion.collectAsStateWithLifecycle()
    val listEmptySuggestion by viewModel.listEmptySuggestion.collectAsStateWithLifecycle()
    val listPrioritySuggestion by viewModel.listPrioritySuggestion.collectAsStateWithLifecycle()
    val listAssignmentSuggestion by viewModel.listAssignmentSuggestion.collectAsStateWithLifecycle()
    val listDuplicateSuggestion by viewModel.listDuplicateSuggestion.collectAsStateWithLifecycle()

    Column(Modifier.padding(bottom = 80.dp)) {
        ListApplication(listPrioritySuggestion,"Requiere Atención Urgente",Icons.Default.Warning)
        ListApplication(listLowerSuggestion,"Actualizar para Soporte Google Play",Icons.Default.Refresh)
        ListApplication(listAssignmentSuggestion,"Evento Sin Asignar",Icons.Default.AccountCircle)
        ListApplication(listEmptySuggestion,"Evento Finalizado Sin Fecha de Finalización",Icons.Default.DateRange)
        ListApplication(listDuplicateSuggestion,"2 Aplicaciones del mismo tipo",Icons.Default.Notifications)
    }

}

@Composable
fun ListApplication(
    listApplication: List<Suggestion>,
    message:String,
    icon: ImageVector
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(listApplication) { item ->
            AccessItemLog(item,message,icon)
        }
    }
}

@Composable
fun AccessItemLog(
    item: Suggestion,
    message: String,
    icon:ImageVector
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.small)
            .background(Color(0xFFD7DDE2))
            .border(width = 3.dp, color = if(message.contains("Urgente")) Color(0xFFFF0000) else Color(0xFF010E3C))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconLog(icon)
            Spacer(modifier = Modifier.size(10.dp))
            Column() {
                Text(text = message, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Aplicación: " + item.appName, style = MaterialTheme.typography.bodySmall)
                Text(text = "Tipo: " + item.type, style = MaterialTheme.typography.bodySmall)
                Text(text = "Detalle: " + item.detail, style = MaterialTheme.typography.bodySmall)
            }

        }
    }
}

@Composable
fun IconLog(icon:ImageVector)
{
    IconButton(onClick = {}) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon, contentDescription = null
            )
        }
    }
}
