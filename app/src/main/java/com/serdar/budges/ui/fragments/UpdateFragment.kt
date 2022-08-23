package com.serdar.budges.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentUpdateBinding
import com.serdar.budges.data.transaction.Transaction
import com.serdar.budges.model.TransactionViewModel

class UpdateFragment : Fragment() {
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.transactionName.setText(args.current.transaction)
        binding.transactionAmounts.setText(args.current.amount.toString())
        binding.transactionDesc.setText(args.current.description)


        binding.updateButton.setOnClickListener {
            updateItem()

        }
        binding.deleteData.setOnClickListener {
            deleteShoppingData()
        }

    }

    private fun updateItem() {
        val transactionName = binding.transactionName.text.toString()
        val transactionAmount = binding.transactionAmounts.text.toString()
        val transactionDesc = binding.transactionDesc.text.toString()

        val updateShopping = Transaction(
            args.current.id,
            transactionName,
            transactionAmount.toDouble(),
            transactionDesc
        )
        transactionViewModel.updateTransaction(updateShopping)
        findNavController().navigate(R.id.action_updateFragment_to_navigation_home)

        if (inputCheck(transactionName, transactionAmount, transactionDesc)) {
            Toast.makeText(requireContext(), "Updated ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(
        transactionName: String,
        transactionAmount: String,
        transactionDesc: String
    ): Boolean {
        return !(TextUtils.isEmpty(transactionName) && TextUtils.isEmpty(transactionAmount) && TextUtils.isEmpty(
            transactionDesc
        ))
    }

    private fun deleteShoppingData() {

        transactionViewModel.deleteTransaction(args.current)
        findNavController().navigate(R.id.action_updateFragment_to_navigation_home)
    }

}