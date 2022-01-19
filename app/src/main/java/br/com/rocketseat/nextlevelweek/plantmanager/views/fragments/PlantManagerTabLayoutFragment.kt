package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentPlantManagerTabLayoutBinding
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPlantManagerTabLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayoutSetup()

        val lastUserRecord: LiveData<User> = userViewModel.getLastUserRecord()

        lastUserRecord.observe(viewLifecycleOwner, Observer { user ->
            binding?.greetingHeader?.txtGreetingUser?.text = getString(R.string.greeting_user, user.userName)
        })
    }


    private fun tabLayoutSetup() {
        val tabLayout: TabLayout = binding?.tblMenu as TabLayout
        val viewPager: ViewPager2 = binding?.viewPagerMenu as ViewPager2
        val tabLayoutAdapter = PlantTabLayoutAdapter(this)

        viewPager.adapter = tabLayoutAdapter
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(tabLayoutAdapter.tabsNames[position])
        }.attach()
    }
}