package sk.uniza.fri.sudora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sk.uniza.fri.sudora.databinding.ActivityMainBinding
import sk.uniza.fri.sudora.notes.Note


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel: NoteListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        viewModel = ViewModelProvider(this).get(NoteListViewModel::class.java)
        readDataToViewModel()
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        Log.d("MainActivity", "onCreate()")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop()")
        writeFromViewModel()
    }

    private fun writeFromViewModel(){
        val gson = Gson()

        val noteJSON = gson.toJson(viewModel.noteList.value)
        val archiveJSON = gson.toJson(viewModel.archiveList.value)
        val trashJSON = gson.toJson(viewModel.trashList.value)

        getPreferences(MODE_PRIVATE).edit().apply {
            putString("noteJSON", noteJSON)
            putString("archiveJSON", archiveJSON)
            putString("trashJSON", trashJSON)
        }.apply()
    }

    private fun readDataToViewModel(){
        val gson = Gson()
        val sharedPrefs = getPreferences(MODE_PRIVATE)

        val noteJSON = sharedPrefs.getString("noteJSON", "[]")
        val archiveJSON = sharedPrefs.getString("archiveJSON", "[]")
        val trashJSON = sharedPrefs.getString("trashJSON", "[]")

        val type = object: TypeToken<MutableList<Note?>>() {}.type
        viewModel.addToViewModel(gson.fromJson(noteJSON, type), gson.fromJson(archiveJSON, type), gson.fromJson(trashJSON, type))
    }
}