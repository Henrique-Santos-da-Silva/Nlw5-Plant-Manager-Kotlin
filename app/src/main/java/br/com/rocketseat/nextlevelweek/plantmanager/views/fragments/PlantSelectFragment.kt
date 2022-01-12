package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantSelectBinding

class PlantSelectFragment : Fragment() {

    private var _binding: FragmentPlantSelectBinding? = null
    private val binding: FragmentPlantSelectBinding? get() = _binding

    private val plantSelectArgs: PlantSelectFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantSelectBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName: String = plantSelectArgs.userNameArgs

        binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, userName)
    }
}