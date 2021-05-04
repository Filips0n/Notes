package sk.uniza.fri.sudora.notes

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import sk.uniza.fri.sudora.R
import sk.uniza.fri.sudora.databinding.FragmentCreateNoteBinding
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [CreateNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    var note = Note(UUID.randomUUID(), "", "")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        binding = DataBindingUtil.inflate<FragmentCreateNoteBinding>(
            inflater, R.layout.fragment_create_note, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener { view : View ->
            view.findNavController().navigate(CreateNoteFragmentDirections.actionCreateNoteFragmentToMainFragment(note))
        }

        binding.doneButton.setOnClickListener {
            if (isText()) {
                saveNote()
                view.findNavController().navigate(CreateNoteFragmentDirections.actionCreateNoteFragmentToMainFragment(note))
            }
        }
    }
    private fun saveNote(){
        val title = binding.noteTitleInput.text.toString()
        val text = binding.noteInput.text.toString()
        note.noteTitle = title
        note.noteText = text
    }

    //https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-programmatically?rq=1
    private fun hideKeyboard(activity: Activity) {
        val view: View = activity.findViewById(android.R.id.content)
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun isText() : Boolean {
        if (binding.noteTitleInput.text.isNullOrEmpty()){
            val toast = Toast.makeText(context, getString(R.string.note_title_is_required), Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
            toast.show()
            return false
        } else if (binding.noteInput.text.isNullOrEmpty()){
            val toast = Toast.makeText(context, getString(R.string.note_description_is_required), Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
            toast.show()
            return false
        }
        return true
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard(this.requireActivity())
    }
}