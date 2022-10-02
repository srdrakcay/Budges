package com.serdar.budges.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.serdar.budges.R
import com.serdar.budges.adapter.IncomeAdapter
import com.serdar.budges.databinding.FragmentIncomeBinding
import com.serdar.budges.ui.viewmodel.IncomeDashViewModel


class IncomeFragment : Fragment() {
    private val incomeDashViewModel by lazy { IncomeDashViewModel(requireActivity().application) }
    private lateinit var incomeAdapter: IncomeAdapter
    private lateinit var binding: FragmentIncomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncomeBinding.inflate(layoutInflater)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPages)
        viewPager?.currentItem = 1
        incomeAdapter = IncomeAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        incomeDashViewModel.readIncome.observe(requireActivity(), Observer { transactionList ->
            incomeAdapter.setIncome(transactionList)

            val incomeAmount = transactionList.sumOf { it.amount }
            binding.income.text = "$ %.2f".format(incomeAmount)

        })

    }
}