package com.serdar.budges.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.serdar.budges.adapter.ExpenseAdapter
import com.serdar.budges.databinding.FragmentExpenseBinding
import com.serdar.budges.ui.viewmodel.ExpenseDashViewModel


class ExpenseFragment : Fragment() {
    private val expenseDashViewModel by lazy { ExpenseDashViewModel(requireActivity().application) }
    private lateinit var expenseAdapter: ExpenseAdapter
    private lateinit var binding: FragmentExpenseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpenseBinding.inflate(layoutInflater)
        expenseAdapter = ExpenseAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        expenseDashViewModel.readExpenseData.observe(
            requireActivity(),
            Observer { transactionList ->
                expenseAdapter.setExpanse(transactionList)

                val expanseAmount = transactionList.sumOf { it.amount }
                binding.expanse.text = "$ %.2f".format(expanseAmount)


            })

    }
}