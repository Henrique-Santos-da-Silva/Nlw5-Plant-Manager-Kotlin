package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantSaveBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class PlantSaveFragment : Fragment() {
    private var _binding: FragmentPlantSaveBinding? = null
    private val binding: FragmentPlantSaveBinding? get() = _binding

    private val plantSaveArgs: PlantSaveFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantSaveBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plant: Plant = plantSaveArgs.plantModel

        binding?.let { psBinding ->
            with(psBinding) {
                val photoUrl: Uri = Uri.parse(plant.photo)
                GlideToVectorYou.init().with(requireContext()).load(photoUrl, imgPlantDetail)
                txtPlantName.text = plant.name
                txtPlantAbout.text = plant.about
                cardWaterTips.txtWaterTips.text = plant.waterTips
            }

        }
    }
}