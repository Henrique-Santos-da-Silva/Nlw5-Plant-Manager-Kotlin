package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantSelectBinding
import br.com.rocketseat.nextlevelweek.plantmanager.utils.Resource
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.PlantViewModel
import com.google.android.material.chip.Chip
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlantSelectFragment : Fragment() {

    private var _binding: FragmentPlantSelectBinding? = null
    private val binding: FragmentPlantSelectBinding? get() = _binding

    private val plantSelectArgs: PlantSelectFragmentArgs by navArgs()

    private val plantViewModel: PlantViewModel by inject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantSelectBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName: String = plantSelectArgs.userNameArgs

        binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, userName)


        plantViewModel.listHomeRooms.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { plantEnvironment ->
                        addChip(plantEnvironment.title)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {

                }
            }
        })

    }

    private fun addChip(text: String) {
        val chip = Chip(requireContext())
        chip.text = text

        chip.isCloseIconVisible = false

        binding?.chipGroupHouseRooms?.addView(chip)
    }
}