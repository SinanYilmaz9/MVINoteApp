package dev.sinanyilmaz.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dev.sinanyilmaz.noteapp.screen.NoteScreen
import dev.sinanyilmaz.noteapp.ui.theme.NoteAppTheme
import dev.sinanyilmaz.noteapp.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val viewModel: NoteViewModel = hiltViewModel()
    NoteScreen(viewModel = viewModel)
}
