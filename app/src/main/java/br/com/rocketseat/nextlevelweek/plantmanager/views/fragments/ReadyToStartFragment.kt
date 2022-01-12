package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentReadyToStartBinding

class ReadyToStartFragment : Fragment() {
    private var _binding: FragmentReadyToStartBinding? = null
    private val binding: FragmentReadyToStartBinding? get() = _binding

    private val readyToStartNav: ReadyToStartFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReadyToStartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnStart?.setOnClickListener {
            val userName: String = readyToStartNav.userNameArgs2
            val actionReadyToStartToPlantSelect: NavDirections = ReadyToStartFragmentDirections.actionReadyToStartFragmentToPlantSelectFragment(userName)
            findNavController().navigate(actionReadyToStartToPlantSelect)
//            findNavController().navigate(R.id.action_readyToStartFragment_to_plantSelectFragment)
        }
    }
}