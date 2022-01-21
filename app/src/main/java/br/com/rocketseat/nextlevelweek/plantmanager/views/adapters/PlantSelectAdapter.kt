package br.com.rocketseat.nextlevelweek.plantmanager.views.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.ItemPlantBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.views.fragments.PlantManagerTabLayoutFragmentDirections
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class PlantSelectAdapter : ListAdapter<Plant, PlantSelectAdapter.PlantSelectViewHolder>(PlantSelectDiffCallBack()) {

    inner class PlantSelectViewHolder(private val binding: ItemPlantBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            val photoUrl: Uri = Uri.parse(plant.photo)

            GlideToVectorYou.init().with(itemView.context).load(photoUrl, binding.imgPlant)

            binding.txtPlantName.text = plant.name

            binding.root.setOnClickListener { view ->
                val tabLayoutFragmentAction: NavDirections = PlantManagerTabLayoutFragmentDirections.actionPlantManagerTabLayoutFragmentToPlantSaveFragment(plant)
                view.findNavController().navigate(tabLayoutFragmentAction)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantSelectViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPlantBinding = ItemPlantBinding.inflate(layoutInflater, parent, false)
        return PlantSelectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantSelectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlantSelectDiffCallBack: DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean = oldItem == newItem
}