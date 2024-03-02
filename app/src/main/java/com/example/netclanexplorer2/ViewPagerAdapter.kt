package com.example.netclanexplorer2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.netclanexplorer2.fragments.MerchantTabFragment
import com.example.netclanexplorer2.fragments.PersonalTabFragment
import com.example.netclanexplorer2.fragments.ProfessionalTabFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PersonalTabFragment()
            1 -> ProfessionalTabFragment()
            2 -> MerchantTabFragment()
            else -> PersonalTabFragment()
        }
    }
}