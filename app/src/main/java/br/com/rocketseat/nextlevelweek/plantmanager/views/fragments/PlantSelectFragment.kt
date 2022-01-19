package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantSelectBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.User
import br.com.rocketseat.nextlevelweek.plantmanager.utils.Resource
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.PlantViewModel
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.UserViewModel
import br.com.rocketseat.nextlevelweek.plantmanager.views.adapters.PlantSelectAdapter
import br.com.rocketseat.nextlevelweek.plantmanager.views.adapters.PlantTabLayoutAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class PlantSelectFragment : Fragment() {

    private var _binding: FragmentPlantSelectBinding? = null
    private val binding: FragmentPlantSelectBinding? get() = _binding
    private val plantAdapter: PlantSelectAdapter = PlantSelectAdapter()

//    private val plantSelectArgs: PlantSelectFragmentArgs by navArgs()

    private val plantViewModel: PlantViewModel by viewModels()

//    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        tabLayoutSetup()
        plantViewModel.getPlants()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantSelectBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val userName: String = plantSelectArgs.userNameArgs

//        val lastUserRecord: LiveData<User> = userViewModel.getLastUserRecord()
//
//        lastUserRecord.observe(viewLifecycleOwner, Observer { user ->
//            binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, user.userName)
//        })

//        binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, userName)


        binding?.rvPlantSelect?.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        binding?.rvPlantSelect?.adapter = plantAdapter

        plantViewModel.listHomeRooms.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { plantEnvironment ->
                        plantEnvironment.forEach {
                            addChip(it.title)
                        }

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

        plantViewModel.listPlants.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { plantList ->
                        plantAdapter.submitList(plantList)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {}
            }
        })

    }

    private fun addChip(text: String) {
        val chip = layoutInflater.inflate(R.layout.home_rooms_chip_choice_filter, null, false) as Chip
        chip.text = text
        chip.isCloseIconVisible = false

//        chip.layout = LayoutInflater.from(requireContext()).inflate(R.layout.home_rooms_chip_choice_filter, null, false) as Chip

//        chip.setOnClickListener {
//            if (chip.isChecked) {
//                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
//                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_green))
//            } else {
//                chip.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
//                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//            }
//        }


//        chip.setBackgroundColor()

        binding?.chipGroupHouseRooms?.addView(chip)
    }


}