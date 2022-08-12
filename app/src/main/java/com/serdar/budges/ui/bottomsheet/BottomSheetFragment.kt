package com.serdar.budges.ui.bottomsheet


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentBottomSheetBinding
import com.serdar.budges.data.Transaction
import com.serdar.budges.model.TransactionViewModel


class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetBinding
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transactionName.addTextChangedListener {
            if (it!!.count() > 0)
                binding.productName.error = null
        }

        binding.transactionAmounts.addTextChangedListener {
            if (it!!.count() > 0)
                binding.transactionAmounts.error = null
        }

        binding.addButton.setOnClickListener {
            insertData()

        }

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(
            requireContext(),
            R.style.Theme_YourApp_NoShapeBottomSheetDialog
        )
    }


    private fun insertData() {
        val label = binding.transactionName.text.toString()
        val description = binding.transactionDesc.text.toString()
        val amount = binding.transactionAmounts.text.toString().toDoubleOrNull()

        if (label.isEmpty())
            binding.productName.error = "Please enter a valid label"
        else if (amount == null)
            binding.transactionAmount.error = "Please enter a valid amount"
        else {
            val transaction = Transaction(0, label, amount, description)
            transactionViewModel.addTransaction(transaction)
            findNavController().navigate(R.id.action_bottomSheetFragment_to_navigation_home)
        }
    }

}
