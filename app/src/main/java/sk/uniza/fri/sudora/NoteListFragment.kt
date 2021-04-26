package sk.uniza.fri.sudora

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import sk.uniza.fri.sudora.notes.NoteViewModel


class NoteListFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()

}