package com.serdar.budges.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdar.budges.adapter.ExpanseAdapter
import com.serdar.budges.databinding.FragmentExpanseDashBinding
import com.serdar.budges.model.TransactionViewModel

class ExpanseDashFragment : Fragment() {
    private lateinit var binding: FragmentExpanseDashBinding
    private lateinit var expanseAdapter: ExpanseAdapter
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpanseDashBinding.inflate(layoutInflater)
        expanseAdapter = ExpanseAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expanseData()
    }

    private fun expanseData() {
        val expanseAdapter = ExpanseAdapter()

        binding.dashExpanse.layoutManager = LinearLayoutManager(requireContext())
        binding.dashExpanse.adapter = expanseAdapter
        transactionViewModel.readExpanseData.observe(
            requireActivity(),
            Observer { transactionList ->
                expanseAdapter.setExpanse(transactionList)

            })
    }
}