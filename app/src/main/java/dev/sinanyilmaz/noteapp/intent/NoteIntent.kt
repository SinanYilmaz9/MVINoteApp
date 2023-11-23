package dev.sinanyilmaz.noteapp.intent

sealed class NoteIntent {
    data class AddNote(val note: String) : NoteIntent()
    data class DeleteNote(val note: String) : NoteIntent()
}