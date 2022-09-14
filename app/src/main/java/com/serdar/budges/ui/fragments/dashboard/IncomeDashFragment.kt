package com.serdar.budges.ui.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdar.budges.adapter.IncomeAdapter
import com.serdar.budges.databinding.FragmentExpanseDashBinding
import com.serdar.budges.databinding.FragmentIncomeDashBinding
import com.serdar.budges.ui.viewmodel.IncomeDashViewModel


class IncomeDashFragment : Fragment() {
    private lateinit var binding: FragmentIncomeDashBinding
    private lateinit var incomeAdapter: IncomeAdapter
    private val incomeDashViewModel by lazy { IncomeDashViewModel(requireActivity().application) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncomeDashBinding.inflate(layoutInflater)
        incomeAdapter = IncomeAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incomeDash()
    }

    private fun incomeDash() {
        val incomeAdapter = IncomeAdapter()

        binding.dashIncome.layoutManager = LinearLayoutManager(requireContext())
        binding.dashIncome.adapter = incomeAdapter
        incomeDashViewModel.readIncome.observe(
            requireActivity(),
            Observer { transactionList ->
                incomeAdapter.setIncome(transactionList)

            })
    }
}