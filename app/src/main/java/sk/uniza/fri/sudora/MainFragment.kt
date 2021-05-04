package sk.uniza.fri.sudora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import sk.uniza.fri.sudora.adapter.NoteAdapter
import sk.uniza.fri.sudora.adapter.NoteListener
import sk.uniza.fri.sudora.databinding.FragmentMainBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    private val args by navArgs<MainFragmentArgs>()
    private val viewModel: NoteListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        binding.newNoteButton.setOnClickListener { view: View ->
            view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToCreateNoteFragment())
        }

        val note = args.note
        if(!isInViewModel() && note != null && note.noteTitle != "" && note.noteText != "") {
            viewModel.addNote(note, ListType.NOTE)
        }

        val adapter = NoteAdapter(viewModel, ListType.NOTE, NoteListener { noteId ->
                Toast.makeText(context, "$noteId", Toast.LENGTH_SHORT).show()
            })
        binding.noteList.adapter = adapter
        binding.lifecycleOwner = this
        ////////
        viewModel.noteList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
            adapter.notifyDataSetChanged()
            if (it.size > 0) {
                binding.textViewNote.text = ""
            } else {
                binding.textViewNote.text = getString(R.string.no_notes_to_display)
            }
        })

        ////////
        val manager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        binding.noteList.layoutManager = manager

        return binding.root
    }

    private fun isInViewModel() : Boolean {
        for (item in viewModel.noteList.value!!) {
            if (args.note?.noteId == item?.noteId) {
                return true
            }
        }
        for (item in viewModel.noteListAllRemoved.value!!) {
            if (args.note?.noteId == item?.noteId) {
                return true
            }
        }
        return false
    }
}