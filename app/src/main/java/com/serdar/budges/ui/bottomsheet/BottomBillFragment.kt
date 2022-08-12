package com.serdar.budges.ui.bottomsheet


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.serdar.budges.R
import com.serdar.budges.databinding.FragmentBottomBillBinding
import com.serdar.budges.model.TransactionViewModel


class BottomBillFragment : BottomSheetDialogFragment() {
    private val transactionViewModel by lazy { TransactionViewModel(requireActivity().application) }

    private lateinit var binding: FragmentBottomBillBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomBillBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(
            requireContext(),
            R.style.Theme_YourApp_NoShapeBottomSheetDialog
        )
    }
}