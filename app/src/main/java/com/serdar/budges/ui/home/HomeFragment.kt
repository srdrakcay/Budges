package com.serdar.budges.ui.home

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentHomeBinding
import com.serdar.budges.adapter.BudgesAdapter
import com.serdar.budges.adapter.HomeCryptoAdapter
import com.serdar.budges.adapter.ViewPagerAdapter
import com.serdar.budges.data.transaction.Transaction
import com.serdar.budges.di.repository.CryptoRepository
import com.serdar.budges.infrastructure.NotificationUtils
import com.serdar.budges.model.CryptoViewModel
import com.serdar.budges.model.CryptoViewModelFactory
import com.serdar.budges.model.TransactionViewModel
import com.serdar.budges.ui.components.BalanceDialog
import com.serdar.budges.ui.fragments.ExpanseFragment
import com.serdar.budges.ui.fragments.IncomeFragment
import com.serdar.budges.ui.fragments.TotalBalanceFragment
import com.serdar.budges.util.Constants.Companion.DESCRIPTION
import com.serdar.budges.util.Constants.Companion.TITLE

class HomeFragment : Fragment() {
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var transaction: List<Transaction>
    private lateinit var budgesAdapter: BudgesAdapter
    private lateinit var viewModel: CryptoViewModel
    private val adapter by lazy { HomeCryptoAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        transaction = arrayListOf()
        budgesAdapter = BudgesAdapter()
        adapterSetup()
        cryptoData()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager()
        Dots()
        swipeToDelete()
        binding.sheetDialog.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_bottomSheetFragment)
        }


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

    private fun adapterSetup() {
        binding.cryview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.cryview.adapter = adapter

    }

    private fun dialog() {
        val firstrun: Boolean = requireActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("firstrun", true);
        if (firstrun) {

            val dialog = BalanceDialog().show(parentFragmentManager, "dialog")
            requireActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("firstrun", false)
                .apply()
        }
    }

    private fun swipeToDelete() {
        val budgesAdapter = BudgesAdapter()

        binding.rvView.layoutManager = LinearLayoutManager(requireContext())
        binding.rvView.adapter = budgesAdapter

        try {
            //reading data and setting data to recyclerview
            transactionViewModel.readAllData.observe(
                requireActivity(),
                Observer { transactionList ->
                    budgesAdapter.setDataTransaction(transactionList)

                    val totalAmount = transactionList.sumOf { it.amount }
                    if (totalAmount > 2500) {
                        dialog()
                    }
                    if (totalAmount <=0) {
                        NotificationUtils.bugesNotification(
                            requireContext(),
                            TITLE,
                            DESCRIPTION
                        )
                    }


                    val itemTouchHelper =
                        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                            override fun onMove(
                                recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder
                            ): Boolean {
                                return false
                            }

                            override fun onSwiped(
                                viewHolder: RecyclerView.ViewHolder,
                                direction: Int
                            ) {

                                val position = viewHolder.adapterPosition
                                transactionViewModel.deleteTransaction(transactionList[position])
                            }

                        }
                    val swipeHelper = ItemTouchHelper(itemTouchHelper)
                    swipeHelper.attachToRecyclerView(binding.rvView)
                })
        } catch (exception: Exception) {
            Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cryptoData() {
        val repository = CryptoRepository()
        val cryptoViewModelFactory = CryptoViewModelFactory(repository)

        viewModel = ViewModelProvider(this, cryptoViewModelFactory).get(CryptoViewModel::class.java)
        viewModel.getData()
        viewModel.myResponse.observe(requireActivity(), Observer {
            adapter.setDatas(it)

        })

    }

}

