package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentMyPlantsBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.PlantDbViewModel
import br.com.rocketseat.nextlevelweek.plantmanager.views.adapters.PlantFavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import org.joda.time.Hours

@AndroidEntryPoint
@FragmentScoped
class MyPlantsFragment : Fragment() {
    private var _binding: FragmentMyPlantsBinding? = null
    private val binding: FragmentMyPlantsBinding? get() = _binding

    private val plantDbViewModel: PlantDbViewModel by viewModels()
    private val favoritePlantAdapter = PlantFavoriteAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMyPlantsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvFavoritePlant?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvFavoritePlant?.adapter = favoritePlantAdapter

        val readFavoritesPlants: LiveData<List<Plant>> = plantDbViewModel.readFavoritesPlants()

        readFavoritesPlants.observe(viewLifecycleOwner, Observer { favoritesPlants ->
            if (favoritesPlants.isNullOrEmpty()) {
                return@Observer
            }

            val timeDistance: Int = reminderForNextWatering(favoritesPlants[0].timeToWater)

            binding?.cardWaterTips?.txtWaterTips?.text = getString(R.string.next_watered, favoritesPlants[0].name, timeDistance)

            favoritePlantAdapter.submitList(favoritesPlants)
        })

        onSwipeDeleteFavoritePlant()
    }

    private fun onSwipeDeleteFavoritePlant() {
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START or ItemTouchHelper.END) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(requireContext(), R.color.candy))
                    .addActionIcon(R.drawable.ic_delete_outline)
                    .create()
                    .decorate()
            }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.bindingAdapterPosition
                val currentFavoritePlant: Plant = favoritePlantAdapter.currentList[position]

                val actionTabLayoutToDeleteDialog: NavDirections = PlantManagerTabLayoutFragmentDirections.actionPlantManagerTabLayoutFragmentToDeleteConfirmDialog(currentFavoritePlant)
                findNavController().navigate(actionTabLayoutToDeleteDialog)

                favoritePlantAdapter.notifyItemChanged(position)
            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavoritePlant)
    }

    private fun reminderForNextWatering(plantTimeSaved: org.joda.time.LocalDateTime): Int {
        val currentDeviceDateTime: org.joda.time.LocalDateTime = org.joda.time.LocalDateTime.now()

        val hoursBetween: Int = if (plantTimeSaved.toLocalTime().isBefore(currentDeviceDateTime.toLocalTime())) {
            Hours.hoursBetween(currentDeviceDateTime, plantTimeSaved.plusDays(1).plusHours(1)).hours
        } else {
            Hours.hoursBetween(currentDeviceDateTime, plantTimeSaved.plusHours(1)).hours
        }
        return hoursBetween
    }
}