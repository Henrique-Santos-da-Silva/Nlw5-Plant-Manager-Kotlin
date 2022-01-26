package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantSaveBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.PlantDbViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class PlantSaveFragment : Fragment() {
    private var _binding: FragmentPlantSaveBinding? = null
    private val binding: FragmentPlantSaveBinding? get() = _binding

    private val plantSaveArgs: PlantSaveFragmentArgs by navArgs()

    private val plantDbViewModel: PlantDbViewModel by viewModels()

    private var hour: Int? = null
    private var minutes: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding?.timePicker?.setIs24HourView(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantSaveBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        val plant: Plant = plantSaveArgs.plantModel

        binding?.let { psBinding ->
            with(psBinding) {
                val photoUrl: Uri = Uri.parse(plant.photo)
                GlideToVectorYou.init().with(requireContext()).load(photoUrl, imgPlantDetail)
                txtPlantName.text = plant.name
                txtPlantAbout.text = plant.about
                cardWaterTips.txtWaterTips.text = plant.waterTips

                psBinding.btnSaveFavoritePlant.setOnClickListener {
                    timePickerSetup()

                    plant.hours = hour ?: 0
                    plant.minutes = minutes ?: 0

                    plantDbViewModel.addPlantInFavorites(plant)
                    findNavController().navigate(R.id.action_plantSaveFragment_to_plantManagerTabLayoutFragment)
                }
            }
        }
    }

    private fun timePickerSetup() {

        binding?.timePicker?.let { timePicker ->
            timePicker.setOnTimeChangedListener { _, selectedHour, selectedMinutes ->
                if (hour != null || hour != 0 && minutes != null || minutes != 0) {
                    hour = null
                    minutes = null

                    hour = selectedHour
                    minutes = selectedMinutes
                }
            }

            if (hour == null && minutes == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.hour
                    minutes = timePicker.minute

                } else {
                    hour = timePicker.currentHour
                    minutes = timePicker.currentMinute
                }
            }
        }
    }
}