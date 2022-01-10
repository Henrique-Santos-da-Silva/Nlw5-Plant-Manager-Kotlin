package br.com.rocketseat.nextlevelweek.plantmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentReadyToStartBinding

class ReadyToStartFragment : Fragment() {
    private var _binding: FragmentReadyToStartBinding? = null
    private val binding: FragmentReadyToStartBinding? get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReadyToStartBinding.inflate(inflater, container, false)
        return binding?.root
    }
}