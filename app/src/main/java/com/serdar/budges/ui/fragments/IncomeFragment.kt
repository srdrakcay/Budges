package com.serdar.budges.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentIncomeBinding
import com.serdar.budges.adapter.BudgesAdapter
import com.serdar.budges.model.TransactionViewModel


class IncomeFragment : Fragment() {
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    private lateinit var budgesAdapter: BudgesAdapter
    private lateinit var binding: FragmentIncomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncomeBinding.inflate(layoutInflater)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPages)
        viewPager?.currentItem = 1
        budgesAdapter = BudgesAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transactionViewModel.readAllData.observe(requireActivity(), Observer { transactionList ->
            budgesAdapter.setDataTransaction(transactionList)

            val budgetAmount = transactionList.filter { it.amount > 0 }.sumOf { it.amount }
            binding.income.text = "$ %.2f".format(budgetAmount)


        })

    }
}