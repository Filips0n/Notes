package sk.uniza.fri.sudora

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import sk.uniza.fri.sudora.databinding.FragmentArchiveBinding
import sk.uniza.fri.sudora.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ArchiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArchiveFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentArchiveBinding>(inflater, R.layout.fragment_archive, container, false)
        return binding.root
    }
}