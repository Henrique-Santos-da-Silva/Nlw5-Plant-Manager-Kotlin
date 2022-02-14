package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantSaveBinding
import br.com.rocketseat.nextlevelweek.plantmanager.models.Plant
import br.com.rocketseat.nextlevelweek.plantmanager.models.PlantWaterFrequency
import br.com.rocketseat.nextlevelweek.plantmanager.services.WaterPlantNotification
import br.com.rocketseat.nextlevelweek.plantmanager.services.WaterPlantNotification.Companion.PLANT_KEY_NOTIFICATION
import br.com.rocketseat.nextlevelweek.plantmanager.services.WaterPlantNotification.Companion.PLANT_KEY_NOTIFICATION_ID
import br.com.rocketseat.nextlevelweek.plantmanager.utils.hideBackButtonToBar
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.PlantDbViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import org.joda.time.LocalDateTime
import java.lang.Math.floor
import java.util.*

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
        (activity as AppCompatActivity).hideBackButtonToBar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantSaveBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupUi() {

        val plant: Plant = plantSaveArgs.plantModel

        binding?.let { psBinding ->
            with(psBinding) {
                val photoUrl: Uri = Uri.parse(plant.photo)
                GlideToVectorYou.init().with(requireContext()).load(photoUrl, imgPlantDetail)
                txtPlantName.text = plant.name
                txtPlantAbout.text = plant.about
                cardWaterTips.txtWaterTips.text = plant.waterTips

                psBinding.btnBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }

                psBinding.btnSaveFavoritePlant.setOnClickListener {
                    timePickerSetup()

                    val calendarHourAndMinutes: Calendar = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, hour!!)
                        set(Calendar.MINUTE, minutes!!)
                    }

                    if (calendarHourAndMinutes.before(GregorianCalendar.getInstance())) {
                        Toast.makeText(requireContext(), "Esse Horario já passou 😥", Toast.LENGTH_SHORT).show()
                    } else {
                        val convertCalendarTimeToWater = LocalDateTime(calendarHourAndMinutes)
                        plant.timeToWater = convertCalendarTimeToWater

                        val notificationId: Long = plantDbViewModel.getLastNotificationId() ?: 0
                        plant.notificationId = notificationId.inc()

                        plantDbViewModel.addPlantInFavorites(plant)

                        plantNotificationSetup(calendarHourAndMinutes, plant.frequency, plant.name, plant.notificationId)
                        findNavController().navigate(R.id.action_plantSaveFragment_to_plantManagerTabLayoutFragment)
                    }
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun plantNotificationSetup(calendarDateTime: Calendar, plantWaterFrequency: PlantWaterFrequency, plantName: String, plantDbId: Long) {
        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val plantNotificationIntent = Intent(activity?.applicationContext, WaterPlantNotification::class.java)
        val currentDateTime: LocalDateTime = LocalDateTime.now()

        plantNotificationIntent.apply {
            putExtra(PLANT_KEY_NOTIFICATION_ID, plantDbId)
            putExtra(PLANT_KEY_NOTIFICATION, "Está na Hora de cuidar da sua $plantName")
        }

        val broadcast: PendingIntent = PendingIntent.getBroadcast(activity?.applicationContext, 0, plantNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val (times: Int, repeatEvery: String) = plantWaterFrequency

        if (repeatEvery == "week") {
            val interval: Double = floor(7 / times.toDouble())
            calendarDateTime.set(Calendar.DAY_OF_MONTH, currentDateTime.dayOfMonth + interval.toInt())
        } else {
            calendarDateTime.add(Calendar.DATE, 1);
        }

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendarDateTime.timeInMillis, broadcast)
    }
}