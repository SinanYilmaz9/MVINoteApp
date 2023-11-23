package dev.sinanyilmaz.noteapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.sinanyilmaz.noteapp.model.NoteState
import dev.sinanyilmaz.noteapp.R
import dev.sinanyilmaz.noteapp.intent.NoteIntent
import dev.sinanyilmaz.noteapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(viewModel: NoteViewModel = hiltViewModel()) {
    val noteState = viewModel.noteState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(64.dp)
                .size(128.dp)
                .clip(CircleShape)
        )

        val textState = remember { mutableStateOf("") }

        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text(stringResource(R.string.enter_your_note)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            /*colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Yellow,
                containerColor = Color.LightGray
            )*/
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (textState.value != "")
                viewModel.processIntent(NoteIntent.AddNote(textState.value))
                textState.value = ""
        }) {
            Text(text = stringResource(R.string.add_note))
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (noteState) {
            is NoteState.Success -> {
                noteState.notes.forEach { note ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = note, fontSize = 18.sp, modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = {
                                viewModel.processIntent(NoteIntent.DeleteNote(note))
                            }
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = stringResource(R.string.delete_note)
                            )
                        }
                    }
                }
            }

            is NoteState.Error -> {
                // Handle error state
                Text(text = noteState.message, color = Color.Red)
            }
        }
    }
}