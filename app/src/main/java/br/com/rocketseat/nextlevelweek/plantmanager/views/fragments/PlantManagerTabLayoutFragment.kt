package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantManagerTabLayoutBinding
import br.com.rocketseat.nextlevelweek.plantmanager.hideBackButtonToBar
import br.com.rocketseat.nextlevelweek.plantmanager.models.User
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
    private var userName: String? = null

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

        val lastUserRecord: LiveData<User> = userViewModel.getLastUserRecord()

        lastUserRecord.observe(viewLifecycleOwner, Observer { user ->
            userName = user.userName
            binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, userName)
        })
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
                    binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, userName)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            val color = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(R.color.green, BlendModeCompat.SRC_IN)
//            tab.icon?.colorFilter = color
            tab.icon = ContextCompat.getDrawable(requireContext(), tabLayoutAdapter.tabsIcons[position])
            tab.text = getString(tabLayoutAdapter.tabsNames[position])

        }.attach()
    }
}