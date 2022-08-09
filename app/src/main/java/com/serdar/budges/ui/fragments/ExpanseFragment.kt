package com.serdar.budges.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentExpanseBinding
import com.serdar.budges.di.adapter.BudgesAdapter
import com.serdar.budges.model.TransactionViewModel


class ExpanseFragment : Fragment() {
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    private lateinit var budgesAdapter: BudgesAdapter
    private lateinit var binding:FragmentExpanseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentExpanseBinding.inflate(layoutInflater)
        budgesAdapter = BudgesAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        transactionViewModel.readAllData.observe(requireActivity(), Observer { transactionList ->
            budgesAdapter.setDataTransaction(transactionList)

            val totalAmount = transactionList.sumOf { it.amount }
            val budgetAmount = transactionList.filter { it.amount > 0 }.sumOf { it.amount }
            val expanseAmount = totalAmount - budgetAmount

            binding.expanse.text = "$ %.2f".format(expanseAmount)


        })

    }
}