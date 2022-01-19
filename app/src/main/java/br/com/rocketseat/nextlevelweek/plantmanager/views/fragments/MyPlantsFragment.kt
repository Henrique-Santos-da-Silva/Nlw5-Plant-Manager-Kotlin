package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentMyPlantsBinding

class MyPlantsFragment : Fragment() {
    private var _binding: FragmentMyPlantsBinding? = null
    private val binding: FragmentMyPlantsBinding? get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMyPlantsBinding.inflate(inflater, container, false)
        return binding?.root
    }
}