package br.com.rocketseat.nextlevelweek.plantmanager.di

import br.com.rocketseat.nextlevelweek.plantmanager.repositories.PlantManagerRepository
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.PlantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val plantManagerModule: Module = module {
    single { PlantManagerRepository() }

    viewModel { PlantViewModel(get()) }
}