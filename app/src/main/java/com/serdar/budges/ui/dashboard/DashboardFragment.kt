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
import com.serdar.budges.R
import com.serdar.budges.adapter.BudgesAdapter
import com.serdar.budges.adapter.ViewPagerDash
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
        pieChart()
        tabLayout()

    }

    private fun pieChart() {
        transactionViewModel.readAllData.observe(requireActivity(), Observer { transactionList ->
            budgesAdapter.setDataTransaction(transactionList)

            //room içine kaydettiğim dataları burada hesaplatıp pasta diliminde gösteriyorum
            val totalAmount = transactionList.sumOf { it.amount }
            val budgetAmount = transactionList.filter { it.amount > 0 }.sumOf { it.amount }
            val expanseAmount = totalAmount - budgetAmount


            // pieList.add(PieEntry(100f, "Total Amount"))
            val pieList = ArrayList<PieEntry>()
            pieList.add(PieEntry(totalAmount.toFloat(), "Total Amount"))
            pieList.add(PieEntry(budgetAmount.toFloat(), "Income"))
            pieList.add(PieEntry(expanseAmount.toFloat(), "Expanse"))


            val colors = ArrayList<Int>()
            colors.add(Color.BLUE)
            colors.add(Color.GREEN)
            colors.add(Color.RED)

            val pieDataSet = PieDataSet(pieList, "")
            pieDataSet.colors = colors


            pieDataSet.sliceSpace = 0f


            val pieData = PieData(pieDataSet)
            binding.pieChart.data = pieData
            binding.pieChart.setUsePercentValues(true)
            binding.pieChart.isDrawHoleEnabled = true
            binding.pieChart.description.isEnabled = false
            binding.pieChart.setEntryLabelColor(R.color.black)
            binding.pieChart.setCenterTextColor(R.color.black)
            binding.pieChart.setCenterTextSize(40f)
            binding.pieChart.setUsePercentValues(true)
            binding.pieChart.animateY(1400, Easing.EaseInOutQuad)

        })
    }

    private fun tabLayout() {
        val adapter = ViewPagerDash(requireActivity().supportFragmentManager, lifecycle)

        binding.dashView.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.dashView) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "İncome"
                }
                1 -> {
                    tab.text = "Expanse"
                }
            }
        }.attach()
    }

}