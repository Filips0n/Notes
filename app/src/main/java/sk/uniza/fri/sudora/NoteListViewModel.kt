package sk.uniza.fri.sudora

import androidx.lifecycle.ViewModel
import sk.uniza.fri.sudora.notes.Note

class NoteListViewModel : ViewModel() {
    private var _notesList: MutableList<Note> = mutableListOf<Note>()
    val noteList: MutableList<Note> = mutableListOf<Note>()
        get() = field?: _notesList
}