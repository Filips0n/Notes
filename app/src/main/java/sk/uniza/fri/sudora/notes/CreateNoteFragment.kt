package sk.uniza.fri.sudora.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import sk.uniza.fri.sudora.R
import sk.uniza.fri.sudora.databinding.FragmentCreateNoteBinding
import sk.uniza.fri.sudora.databinding.FragmentMainBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CreateNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateNoteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentCreateNoteBinding>(inflater, R.layout.fragment_create_note, container, false)
        /*binding.newNoteButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(MainFragmentD.actionTitleFragmentToGameFragment())
        }*/
        return binding.root
    }

}