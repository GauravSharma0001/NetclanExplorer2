package com.example.netclanexplorer2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.netclanexplorer2.databinding.ActivityMainBinding
import com.example.netclanexplorer2.fragments.MerchantTabFragment
import com.example.netclanexplorer2.fragments.PersonalTabFragment
import com.example.netclanexplorer2.fragments.ProfessionalTabFragment
import com.example.netclanexplorer2.fragments.RefineFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var personalTabFragment: PersonalTabFragment
    private lateinit var professionalTabFragment: ProfessionalTabFragment
    private lateinit var merchantTabFragment: MerchantTabFragment
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var refineFragment: RefineFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        initiallyHideAllFragments()

        setTabLayoutWithViewPager()

        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.exploreFragment2->{
                    showFragment(personalTabFragment)
                    showTabLayout()
                    true
                }
                R.id.refineFragment2->{
                    showFragment(refineFragment)
                    hideTabLayout()
                    true
                }
                else -> false
            }
        }
    }

    private fun hideTabLayout() {
        binding.tablayout.visibility= View.GONE
    }

    private fun showTabLayout() {
        binding.tablayout.visibility = View.VISIBLE
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(personalTabFragment)
            .hide(professionalTabFragment)
            .hide(merchantTabFragment)
            .hide(refineFragment)
            .show(fragment)
            .commitNow()
        //conditions
        binding.viewPager.visibility=if(fragment==refineFragment) View.GONE else View.VISIBLE
        binding.framelayout.visibility=if(fragment==refineFragment) View.VISIBLE else View.GONE

    }

    private fun setTabLayoutWithViewPager() {
        TabLayoutMediator(binding.tablayout, binding.viewPager){tab,positions ->
            tab.text = when(positions){
                0 ->"Personal"
                1 ->"Professional"
                2-> "Merchant"
                else -> ""
            }
        }.attach()
    }

    private fun initiallyHideAllFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container,personalTabFragment)
            .hide(personalTabFragment)
            .add(R.id.container,professionalTabFragment)
            .hide(professionalTabFragment)
            .add(R.id.container,merchantTabFragment)
            .hide(merchantTabFragment)
            .add(R.id.container,refineFragment)
            .hide(refineFragment)
            .commitNow()


    }

    private fun initialize() {
        personalTabFragment = PersonalTabFragment()
        professionalTabFragment = ProfessionalTabFragment()
        merchantTabFragment = MerchantTabFragment()
        refineFragment = RefineFragment()


        viewPagerAdapter = ViewPagerAdapter(this)
        binding.viewPager.adapter=viewPagerAdapter
        binding.viewPager.offscreenPageLimit = 2

    }
}