package br.com.rocketseat.nextlevelweek.plantmanager.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.ActivityMainBinding
import br.com.rocketseat.nextlevelweek.plantmanager.services.WaterPlantNotification
import br.com.rocketseat.nextlevelweek.plantmanager.services.WaterPlantNotification.Companion.FRAGMENT_ID
import br.com.rocketseat.nextlevelweek.plantmanager.services.WaterPlantNotification.Companion.FRAGMENT_NAME
import br.com.rocketseat.nextlevelweek.plantmanager.views.fragments.MyPlantsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.appBarMain.toolbar.title = ""
        setSupportActionBar(binding.appBarMain.toolbar)

        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        when(intent?.getStringExtra(FRAGMENT_ID)) {
            FRAGMENT_NAME -> MyPlantsFragment::class.java
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}