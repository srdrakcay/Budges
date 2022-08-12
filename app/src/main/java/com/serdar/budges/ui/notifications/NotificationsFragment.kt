package com.serdar.budges.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.serdar.budges.R
import com.serdar.budges.adapter.BudgesAdapter
import com.serdar.budges.data.Transaction
import com.serdar.budges.databinding.FragmentNotificationsBinding
import com.serdar.budges.model.TransactionViewModel

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    private lateinit var transaction: List<Transaction>
    private lateinit var budgesAdapter: BudgesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding=FragmentNotificationsBinding.inflate(layoutInflater)
        binding.buttonBill.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_bottomBillFragment)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}