package com.serdar.budges.ui.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.serdar.budges.R
import com.serdar.budges.data.transaction.Transaction
import com.serdar.budges.databinding.DialogBalanceBinding
import com.serdar.budges.ui.viewmodel.TransactionViewModel


class BalanceDialog : DialogFragment() {
    private lateinit var binding: DialogBalanceBinding
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }
    private val args by navArgs<BalanceDialogArgs>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogBalanceBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        binding.transactionName.setText(args.currentItem.transaction)
        binding.transactionAmounts.setText(args.currentItem.amount.toString())
        binding.transactionDesc.setText(args.currentItem.description)

        binding.updateButton.setOnClickListener {
            updateItem()
        }
        binding.deleteData.setOnClickListener {
            deleteTransactionData()
        }
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun deleteTransactionData() {

        transactionViewModel.deleteTransaction(args.currentItem)
        findNavController().navigate(R.id.action_balanceDialog_to_navigation_home)

    }

    private fun updateItem() {
        val transactionName = binding.transactionName.text.toString()
        val transactionAmount = binding.transactionAmounts.text.toString()
        val transactionDesc = binding.transactionDesc.text.toString()

        val updateTransaction = Transaction(
            args.currentItem.id,
            transactionName,
            transactionAmount.toDouble(),
            transactionDesc,
            args.currentItem.incomeExpanseType
        )
        transactionViewModel.updateTransaction(updateTransaction)
        findNavController().navigate(R.id.action_balanceDialog_to_navigation_home)

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


}