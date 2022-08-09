package com.serdar.budges.di.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.serdar.budges.R
import com.serdar.budges.di.data.Transaction
import com.serdar.budges.ui.home.HomeFragmentDirections

class BudgesAdapter :
    RecyclerView.Adapter<BudgesAdapter.TransactionHolder>() {
    private var transactionList = emptyList<Transaction>()

    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val desc = view.findViewById<TextView>(R.id.desc)
        val transactions = view.findViewById<TextView>(R.id.transaction)
        val amount = view.findViewById<TextView>(R.id.amount)


        fun bind(transaction: Transaction) {
            transactions.setText(transaction.transaction.toString())
            amount.setText(transaction.amount.toString())
            desc.setText(transaction.description.toString())

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToUpdateFragment(transaction)
            holder.itemView.findNavController().navigate(action)
        }
        val context = holder.amount.context

        if (transaction.amount >= 0) {
            holder.amount.text = "+$%.2f".format(transaction.amount)
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green))

        } else {
            holder.amount.text = "-$%.2f".format(Math.abs(transaction.amount))
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        holder.transactions.text = transaction.transaction

        val color = (Math.random() * 16777215).toInt() or (0xFF shl 24)

        holder.desc.setTextColor(color)
    }


    override fun getItemCount(): Int {

        return transactionList.size

    }

    fun setDataTransaction(transactionList: List<Transaction>) {
        this.transactionList = transactionList
        notifyDataSetChanged()
    }

}