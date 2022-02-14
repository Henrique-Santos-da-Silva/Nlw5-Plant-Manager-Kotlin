package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.DialogDeleteBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.services.WaterPlantNotification
import br.com.rocketseat.nextlevelweek.plantmanager.utils.hideBackButtonToBar
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.PlantDbViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class DeleteConfirmDialogFragment : DialogFragment() {

    private var _binding: DialogDeleteBinding? = null
    private val binding: DialogDeleteBinding? get() = _binding

    private val plantDbViewModel: PlantDbViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogDeleteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantDialogArg: DeleteConfirmDialogFragmentArgs by navArgs()
        val plant: Plant = plantDialogArg.plantToDelete


        binding?.let { dialogDelete ->
            with(dialogDelete) {
                val photoUrl: Uri = Uri.parse(plant.photo)
                GlideToVectorYou.init().with(requireContext()).load(photoUrl, imgPlantDialog)
                txtConfirmDelete.text = getString(R.string.delete_confirm, plant.name)

                btnDialogConfirm.setOnClickListener {
                    plantNotificationCancel(plant.notificationId)
                    plantDbViewModel.deletePlantFavorite(plant)
                    dismiss()
                }

                btnDialogCancel.setOnClickListener {
                    dismiss()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).hideBackButtonToBar()
    }

    private fun plantNotificationCancel(plantDbId: Long) {
        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val plantNotificationIntent = Intent(activity?.applicationContext, WaterPlantNotification::class.java)

        plantNotificationIntent.apply { getLongExtra(WaterPlantNotification.PLANT_KEY_NOTIFICATION_ID, plantDbId) }

        val broadcast: PendingIntent = PendingIntent.getBroadcast(activity?.applicationContext, 0, plantNotificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        alarmManager.cancel(broadcast)
    }
}