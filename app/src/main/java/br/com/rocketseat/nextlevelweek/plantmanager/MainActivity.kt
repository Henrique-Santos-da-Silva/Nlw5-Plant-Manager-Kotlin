package br.com.rocketseat.nextlevelweek.plantmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}