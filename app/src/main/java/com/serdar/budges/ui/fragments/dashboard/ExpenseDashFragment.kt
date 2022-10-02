package com.serdar.budges.ui.fragments.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdar.budges.adapter.ExpenseAdapter
import com.serdar.budges.databinding.FragmentExpenseDashBinding
import com.serdar.budges.ui.viewmodel.ExpenseDashViewModel

class ExpenseDashFragment : Fragment() {
    private lateinit var binding: FragmentExpenseDashBinding
    private lateinit var expenseAdapter: ExpenseAdapter
    private val expenseDashViewModel by lazy { ExpenseDashViewModel(requireActivity().application) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpenseDashBinding.inflate(layoutInflater)
        expenseAdapter = ExpenseAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expanseData()
    }

    private fun expanseData() {
        val expenseAdapter = ExpenseAdapter()

        binding.dashExpense.layoutManager = LinearLayoutManager(requireContext())
        binding.dashExpense.adapter = expenseAdapter
        expenseDashViewModel.readExpenseData.observe(
            requireActivity(),
            Observer { transactionList ->
                expenseAdapter.setExpanse(transactionList)

            })
    }
}