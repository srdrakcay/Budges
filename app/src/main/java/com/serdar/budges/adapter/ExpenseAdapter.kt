package com.serdar.budges.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.serdar.budges.R
import com.serdar.budges.data.transaction.Transaction

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.TransactionHolder>() {
    private var transactionList = emptyList<Transaction>()

    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val desc = view.findViewById<TextView>(R.id.descExpanse)
        val transactions = view.findViewById<TextView>(R.id.transactionExpanse)
        val amount = view.findViewById<TextView>(R.id.amountExpanse)
        val amountView = view.findViewById<ImageView>(R.id.amountViewExpanse)

        fun bind(transaction: Transaction) {
            transactions.text = transaction.transaction.toString()
            amount.text = transaction.amount.toString()
            desc.text = transaction.description.toString()

        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseAdapter.TransactionHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.expanse_item, parent, false)

        return ExpenseAdapter.TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseAdapter.TransactionHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
        val context = holder.amount.context
        holder.amount.text = "-$%.2f".format(transaction.amount)
        holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        holder.amountView.setImageResource(R.drawable.expensesline)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    fun setExpanse(transactionList: List<Transaction>) {
        this.transactionList = transactionList
        notifyDataSetChanged()
    }
}