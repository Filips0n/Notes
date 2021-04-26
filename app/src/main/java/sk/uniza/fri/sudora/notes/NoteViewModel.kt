package sk.uniza.fri.sudora.notes

import androidx.lifecycle.ViewModel
import java.util.*

class NoteViewModel : ViewModel() {
    private var _notesList: MutableList<Note> = mutableListOf<Note>()
    val noteList: MutableList<Note> = mutableListOf<Note>()
            get() = field?: _notesList
}