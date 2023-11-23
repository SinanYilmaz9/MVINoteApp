package dev.sinanyilmaz.noteapp.model

sealed class NoteState {
    data class Success(val notes: List<String>) : NoteState()
    data class Error(val message: String) : NoteState()
}