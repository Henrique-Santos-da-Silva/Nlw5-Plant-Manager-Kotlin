package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentReadyToStartBinding
import br.com.rocketseat.nextlevelweek.plantmanager.hideBackButtonToBar

class ReadyToStartFragment : Fragment() {
    private var _binding: FragmentReadyToStartBinding? = null
    private val binding: FragmentReadyToStartBinding? get() = _binding

//    private val readyToStartNav: ReadyToStartFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).hideBackButtonToBar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReadyToStartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnStart?.setOnClickListener {
//            val userName: String = readyToStartNav.userNameArgs2
//            val actionReadyToStartToPlantSelect: NavDirections = ReadyToStartFragmentDirections.actionReadyToStartFragmentToPlantSelectFragment(userName)
//            findNavController().navigate(actionReadyToStartToPlantSelect)
            findNavController().navigate(R.id.action_readyToStartFragment_to_plantManagerTabLayoutFragment)
        }
    }
}