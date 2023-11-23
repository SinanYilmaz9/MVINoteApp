package dev.sinanyilmaz.noteapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.sinanyilmaz.noteapp.model.NoteState
import dev.sinanyilmaz.noteapp.intent.NoteIntent

class NoteViewModel : ViewModel() {
    private val _noteState = mutableStateOf<NoteState>(NoteState.Success(notes = emptyList()))
    val noteState: State<NoteState> = _noteState

    fun processIntent(intent: NoteIntent) {
        _noteState.value = reduce(_noteState.value, intent)
    }

    private fun reduce(currentState: NoteState, intent: NoteIntent): NoteState {
        return when (intent) {
            is NoteIntent.AddNote -> {
                if (currentState is NoteState.Success) {
                    val updatedNotes = currentState.notes.toMutableList()
                    updatedNotes.add(intent.note)
                    NoteState.Success(notes = updatedNotes)
                } else {
                    currentState
                }
            }
            is NoteIntent.DeleteNote -> {
                if (currentState is NoteState.Success) {
                    val updatedNotes = currentState.notes.toMutableList()
                    updatedNotes.remove(intent.note)
                    NoteState.Success(notes = updatedNotes)
                } else {
                    currentState
                }
            }
        }
    }
}