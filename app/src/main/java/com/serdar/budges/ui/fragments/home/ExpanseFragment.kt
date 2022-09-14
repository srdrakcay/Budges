package com.serdar.budges.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.serdar.budges.adapter.ExpanseAdapter
import com.serdar.budges.databinding.FragmentExpanseBinding
import com.serdar.budges.ui.viewmodel.ExpanseDashViewModel


class ExpanseFragment : Fragment() {
    private val expanseDashViewModel by lazy { ExpanseDashViewModel(requireActivity().application) }
    private lateinit var expanseAdapter: ExpanseAdapter
    private lateinit var binding: FragmentExpanseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExpanseBinding.inflate(layoutInflater)
        expanseAdapter = ExpanseAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        expanseDashViewModel.readExpanseData.observe(
            requireActivity(),
            Observer { transactionList ->
                expanseAdapter.setExpanse(transactionList)

                val expanseAmount = transactionList.sumOf { it.amount }
                binding.expanse.text = "$ %.2f".format(expanseAmount)


            })

    }
}