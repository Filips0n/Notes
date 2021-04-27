package sk.uniza.fri.sudora

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import sk.uniza.fri.sudora.databinding.FragmentArchiveBinding
import sk.uniza.fri.sudora.databinding.FragmentTrashBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TrashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTrashBinding>(inflater, R.layout.fragment_trash, container, false)
        return binding.root
    }
}