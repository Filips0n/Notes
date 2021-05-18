package sk.uniza.fri.sudora

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import sk.uniza.fri.sudora.adapter.NoteAdapter
import sk.uniza.fri.sudora.adapter.NoteListener
import sk.uniza.fri.sudora.databinding.FragmentSettingsBinding
import sk.uniza.fri.sudora.databinding.FragmentTrashBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TrashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater, R.layout.fragment_settings, container, false)

        val appSettingsPrefs: SharedPreferences = this.requireContext().getSharedPreferences(getString(R.string.app_settings_prefs), 0)
        val sharedPrefsEdit : SharedPreferences.Editor = appSettingsPrefs.edit()
        val isNightModeOn: Boolean = appSettingsPrefs.getBoolean(getString(R.string.dark_modeKey), false)

        val switchDarkMode = binding.darkModeSwitch
        switchDarkMode.isChecked = isNightModeOn
        switchDarkMode.setOnClickListener {
            if (isNightModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean(getString(R.string.dark_modeKey), false)
                sharedPrefsEdit.apply()
                Toast.makeText(this.context, getString(R.string.dark_mode_off), Toast.LENGTH_SHORT).show()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean(getString(R.string.dark_modeKey), true)
                sharedPrefsEdit.apply()
                Toast.makeText(this.context, getString(R.string.dark_mode_on), Toast.LENGTH_SHORT).show()
            }
        }

        val isNewNoteTopON: Boolean = appSettingsPrefs.getBoolean(getString(R.string.new_note_top), false)
        val newNoteToTop = binding.newNotesAddSwitch
        newNoteToTop.isChecked = isNewNoteTopON
        newNoteToTop.setOnClickListener {
            if (isNewNoteTopON){
                sharedPrefsEdit.putBoolean(getString(R.string.new_note_top), false)
                sharedPrefsEdit.apply()
            } else {
                sharedPrefsEdit.putBoolean(getString(R.string.new_note_top), true)
                sharedPrefsEdit.apply()
            }
        }
        return binding.root
    }
}