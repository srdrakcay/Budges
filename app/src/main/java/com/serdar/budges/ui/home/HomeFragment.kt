package com.serdar.budges.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentHomeBinding
import com.serdar.budges.adapter.BudgesAdapter
import com.serdar.budges.adapter.ViewPagerAdapter
import com.serdar.budges.data.Transaction
import com.serdar.budges.model.TransactionViewModel
import com.serdar.budges.service.TransactionDatabase
import com.serdar.budges.ui.fragments.ExpanseFragment
import com.serdar.budges.ui.fragments.IncomeFragment
import com.serdar.budges.ui.fragments.TotalBalanceFragment

class HomeFragment : Fragment() {
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var transaction: List<Transaction>
    private lateinit var budgesAdapter: BudgesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        transaction = arrayListOf()
        budgesAdapter = BudgesAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager()
        Dots()

        binding.sheetDialog.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_bottomSheetFragment)
        }
        val budgesAdapter = BudgesAdapter()

        binding.rvView.layoutManager = LinearLayoutManager(requireContext())
        binding.rvView.adapter = budgesAdapter

        transactionViewModel.readAllData.observe(requireActivity(), Observer { transactionList ->
            budgesAdapter.setDataTransaction(transactionList)

            val itemTouchHelper =
                object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                        val position = viewHolder.adapterPosition
                        transactionViewModel.deleteTransaction(transactionList[position])
                    }

                }
            val swipeHelper = ItemTouchHelper(itemTouchHelper)
            swipeHelper.attachToRecyclerView(binding.rvView)
        })

    }

    private fun viewPager() {
        val fragmentList = arrayListOf<Fragment>(
            TotalBalanceFragment(),
            IncomeFragment(),
            ExpanseFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        Handler(Looper.getMainLooper()).post {
            binding.viewPages.adapter = adapter
        }
    }

    private fun Dots() {
        val fragmentList = arrayListOf<Fragment>(
            TotalBalanceFragment(),
            IncomeFragment(),
            ExpanseFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.viewPages
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

    }
}