package com.serdar.budges.ui.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serdar.budges.R
import com.serdar.budges.adapter.OnBoardingAdapter
import com.serdar.budges.adapter.ViewPagerAdapter
import com.serdar.budges.databinding.FragmentViewPagerBinding
import com.serdar.budges.ui.fragments.home.ExpanseFragment
import com.serdar.budges.ui.fragments.home.IncomeFragment
import com.serdar.budges.ui.fragments.home.TotalBalanceFragment


class ViewPagerFragment : Fragment() {

    private lateinit var binding:FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentViewPagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPages()
        Dots()
    }
    private fun setPages(){
        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()

        )
        val adapter = OnBoardingAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        Handler(Looper.getMainLooper()).post {
            binding.viewPager.adapter = adapter
        }
    }
    private fun Dots() {
        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        val adapter = OnBoardingAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val dotsIndicator = binding.dotsOnboarding
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

    }
}