package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantManagerTabLayoutBinding
import br.com.rocketseat.nextlevelweek.plantmanager.utils.hideBackButtonToBar
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.UserViewModel
import br.com.rocketseat.nextlevelweek.plantmanager.views.adapters.PlantTabLayoutAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class PlantManagerTabLayoutFragment : Fragment() {
    private var _binding: FragmentPlantManagerTabLayoutBinding? = null
    private val binding: FragmentPlantManagerTabLayoutBinding? get() = _binding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).hideBackButtonToBar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantManagerTabLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayoutSetup()

        binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, userViewModel.getUser())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).hideBackButtonToBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).hideBackButtonToBar()
    }

    private fun tabLayoutSetup() {
        val tabLayout: TabLayout = binding?.tblMenu as TabLayout
        val viewPager: ViewPager2 = binding?.viewPagerMenu as ViewPager2
        val tabLayoutAdapter = PlantTabLayoutAdapter(this)

        viewPager.adapter = tabLayoutAdapter
        viewPager.isUserInputEnabled = false

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 1) {
                    binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.my_plants_header)
                } else {
                    binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, userViewModel.getUser())
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.icon = ContextCompat.getDrawable(requireContext(), tabLayoutAdapter.tabsIcons[position])
            tab.text = getString(tabLayoutAdapter.tabsNames[position])

        }.attach()
    }
}