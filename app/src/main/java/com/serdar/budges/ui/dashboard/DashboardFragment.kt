package com.serdar.budges.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.serdar.budges.databinding.FragmentDashboardBinding
import com.serdar.budges.di.data.Transaction
import com.serdar.budges.model.TransactionViewModel

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)

        return binding.root
    }


}