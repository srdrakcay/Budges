package com.serdar.budges.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.serdar.budges.ui.fragments.ExpanseDashFragment
import com.serdar.budges.ui.fragments.IncomeDashFragment

class ViewPagerDash (fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0->{
                IncomeDashFragment()
            }
            1->{
                ExpanseDashFragment()
            }else->{
                Fragment()
            }
        }
    }
}