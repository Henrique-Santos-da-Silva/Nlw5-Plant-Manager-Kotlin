package br.com.rocketseat.nextlevelweek.plantmanager.views.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.views.fragments.MyPlantsFragment
import br.com.rocketseat.nextlevelweek.plantmanager.views.fragments.PlantSelectFragment

class PlantTabLayoutAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    val tabsNames: Array<Int> = arrayOf(R.string.save_new_plant, R.string.my_plants)
    val tabsIcons: Array<Int> = arrayOf(R.drawable.ic_add_circle, R.drawable.ic_list_bulleted)
    val fragments: Array<Fragment> = arrayOf(PlantSelectFragment(), MyPlantsFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}