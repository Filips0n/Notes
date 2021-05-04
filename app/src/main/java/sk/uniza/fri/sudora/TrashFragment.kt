package sk.uniza.fri.sudora

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import sk.uniza.fri.sudora.adapter.NoteAdapter
import sk.uniza.fri.sudora.adapter.NoteListener
import sk.uniza.fri.sudora.databinding.FragmentArchiveBinding
import sk.uniza.fri.sudora.databinding.FragmentTrashBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TrashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrashFragment : Fragment() {
    private val viewModel: NoteListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val binding = DataBindingUtil.inflate<FragmentTrashBinding>(inflater, R.layout.fragment_trash, container, false)

        val adapter = NoteAdapter(viewModel, ListType.TRASH ,NoteListener { noteId ->
            Toast.makeText(context, "$noteId", Toast.LENGTH_LONG).show()
        })
        binding.trashList.adapter = adapter
        viewModel.trashList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
            adapter.notifyDataSetChanged()
            if (it.size > 0) {
                binding.textViewTrash.text = ""
            }  else {
                binding.textViewTrash.text = getString(R.string.your_trash_is_empty)
            }
        })
        return binding.root
    }
}