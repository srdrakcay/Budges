package com.serdar.budges.ui.dashboard


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.tabs.TabLayoutMediator
import com.serdar.budges.adapter.BudgesAdapter
import com.serdar.budges.adapter.ViewPagerDash
import com.serdar.budges.databinding.FragmentDashboardBinding
import com.serdar.budges.ui.viewmodel.TransactionViewModel

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
        pieChart()
        tabLayout()

    }

    private fun pieChart() {
        transactionViewModel.readAllData.observe(requireActivity(), Observer { transactionList ->
            budgesAdapter.setDataTransaction(transactionList)

            val income =transactionList.filter { it.incomeExpenseType=="INCOME"  }.sumOf { it.amount }
            val expanse =transactionList.filter { it.incomeExpenseType=="EXPENSE"  }.sumOf { it.amount }
            val total = income - expanse


            // pieList.add(PieEntry(100f, "Total Amount"))
            val pieList = ArrayList<PieEntry>()
            pieList.add(PieEntry(total.toFloat(), "Total Amount"))
            pieList.add(PieEntry(income.toFloat(), "Income"))
            pieList.add(PieEntry(expanse.toFloat(), "Expense"))


            val colorSet = ArrayList<Int>()
            colorSet.add(Color.rgb(255, 107, 107))  //red
            colorSet.add(Color.rgb(173, 232, 244))  // blue
            colorSet.add(Color.rgb(216, 243, 220))  // green

            val pieDataSet = PieDataSet(pieList, "")
            pieDataSet.colors = colorSet


            pieDataSet.sliceSpace = 0f


            val pieData = PieData(pieDataSet)
            binding.pieChart.data = pieData
            binding.pieChart.isDrawHoleEnabled = true
            binding.pieChart.description.isEnabled = false
            binding.pieChart.legend.isEnabled = true
            binding.pieChart.setCenterTextColor(Color.BLACK)
            binding.pieChart.setEntryLabelColor(Color.BLACK)
            binding.pieChart.centerTextRadiusPercent = 0f
            binding.pieChart.animateY(1400, Easing.EaseInOutQuad)

        })
    }

    private fun tabLayout() {
        val adapter = ViewPagerDash(requireActivity().supportFragmentManager, lifecycle)

        binding.dashView.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.dashView) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Income"
                }
                1 -> {
                    tab.text = "Expense"
                }
            }
        }.attach()
    }

}