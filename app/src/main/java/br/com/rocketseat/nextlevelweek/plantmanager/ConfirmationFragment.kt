package br.com.rocketseat.nextlevelweek.plantmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment() {
    private var _binding: FragmentConfirmationBinding? = null
    private val binding: FragmentConfirmationBinding? get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        return binding?.root
    }
}