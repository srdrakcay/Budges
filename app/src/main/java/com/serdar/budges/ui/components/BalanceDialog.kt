package com.serdar.budges.ui.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.serdar.budges.databinding.DialogBalanceBinding


class BalanceDialog(

):DialogFragment() {
    private lateinit var binding:DialogBalanceBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding=DialogBalanceBinding.inflate(layoutInflater)

        val builder =AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog=builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}