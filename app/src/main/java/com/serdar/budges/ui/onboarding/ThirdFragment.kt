package com.serdar.budges.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentThirdBinding
import com.serdar.budges.ui.splash.HomeActivity


class ThirdFragment : Fragment() {

private lateinit var binding:FragmentThirdBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.thirdNext.setOnClickListener {
            onBoardingFinished()
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        binding.thirdBack.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 1
        }
    }
    private fun onBoardingFinished() {

        val sp = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}