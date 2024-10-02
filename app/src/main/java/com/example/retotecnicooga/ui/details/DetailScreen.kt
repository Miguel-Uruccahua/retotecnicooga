package com.example.retotecnicooga.ui.details

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.retotecnicooga.domain.model.Log
import com.example.retotecnicooga.domain.model.LogDetail

@Composable
fun DetailScreen(viewModel: DetailsViewModel = hiltViewModel()){

    val listLog by viewModel.listLogState.collectAsStateWithLifecycle()
    ListApplication(listLog)
}

@Composable
fun ListApplication(
    listApplication: List<Log>,
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.padding(bottom = 80.dp)
    ) {
        items(listApplication, key = { it.id }) { item ->
            AccessItemLog(item)
        }
    }
}

@Composable
fun AccessItemLog(
    item: Log,
) {
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
            IconLog(item.action)

            Spacer(modifier = Modifier.size(10.dp))

            Column() {
                Text(text =  item.action.value, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Referencia: ${item.reference}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Fecha: ${item.date}", style = MaterialTheme.typography.bodySmall)
            }

        }
    }
}

@Composable
fun IconLog(logDetail: LogDetail)
{
    var icon = when(logDetail){
        LogDetail.ADDAPP -> {
            Icons.Default.AddCircle
        }
        LogDetail.ADDAPPDETAIL -> {
            Icons.Default.AddCircle
        }
        LogDetail.DETELEAPP -> {
            Icons.Default.Delete
        }
        LogDetail.UPDATEAPPDETAIL -> {
            Icons.Default.Refresh
        }
    }

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
