package com.serdar.budges.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import com.serdar.budges.R
import com.serdar.budges.adapter.BudgesAdapter
import com.serdar.budges.databinding.FragmentDashboardBinding
import com.serdar.budges.model.TransactionViewModel

class DashboardFragment : Fragment() {
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    private lateinit var budgesAdapter: BudgesAdapter
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
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


            val listPie = ArrayList<PieEntry>()
            listPie.add(PieEntry(100f, "Total Amount"))
            listPie.add(PieEntry(40f, "Income"))
            listPie.add(PieEntry(50f, "Expanse"))

            val colors = ArrayList<Int>()
            colors.add(Color.BLUE)
            colors.add(Color.BLACK)
            colors.add(Color.RED)
            totalAmount.toFloat().apply { colors.add((Color.BLACK)) }

            val pieDataSet = PieDataSet(listPie, "")
            pieDataSet.colors = colors
            pieDataSet.sliceSpace=3f

            val pieData = PieData(pieDataSet)
            binding.pieChart.data = pieData
            binding.pieChart.setUsePercentValues(true)
            binding.pieChart.isDrawHoleEnabled = true
            binding.pieChart.description.isEnabled = false
            binding.pieChart.setEntryLabelColor(R.color.black)
            binding.pieChart.animateY(1400, Easing.EaseInOutQuad)


        })


    }


}