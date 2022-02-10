package br.com.rocketseat.nextlevelweek.plantmanager.views.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.ItemFavoritePlantBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.views.fragments.PlantManagerTabLayoutFragmentDirections
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class PlantFavoriteAdapter : ListAdapter<Plant, PlantFavoriteAdapter.PlantFavoriteViewHolder>(PlantFavoriteDiffCallBack()) {

    inner class PlantFavoriteViewHolder(private val binding: ItemFavoritePlantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            with(binding) {
                val photoUrl: Uri = Uri.parse(plant.photo)
                GlideToVectorYou.init().with(itemView.context).load(photoUrl, imgAvatarPlant)

                txtPlantName.text = plant.name


                val hours: Int = plant.timeToWater.hourOfDay
                val minutes: Int = plant.timeToWater.minuteOfHour

                val completeTime = "$hours : $minutes"

                txtWaterTimer.text = itemView.resources.getString(R.string.water_timer, completeTime)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantFavoriteViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemFavoritePlantBinding = ItemFavoritePlantBinding.inflate(layoutInflater, parent, false)
        return PlantFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantFavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlantFavoriteDiffCallBack : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean = oldItem == newItem

}