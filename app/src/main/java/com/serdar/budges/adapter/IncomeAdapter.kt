package com.serdar.budges.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.serdar.budges.R
import com.serdar.budges.data.transaction.Transaction

class IncomeAdapter: RecyclerView.Adapter<IncomeAdapter.TransactionHolder>() {
    private var transactionList = emptyList<Transaction>()

    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val desc = view.findViewById<TextView>(R.id.descIncome)
        val transactions = view.findViewById<TextView>(R.id.transactionIncome)
        val amount = view.findViewById<TextView>(R.id.amountIncome)
        val amountView = view.findViewById<ImageView>(R.id.amountViewIncome)


        fun bind(transaction: Transaction) {
            transactions.setText(transaction.transaction.toString())
            amount.setText(transaction.amount.toString())
            desc.setText(transaction.description.toString())

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeAdapter.TransactionHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.income_item, parent, false)

        return IncomeAdapter.TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeAdapter.TransactionHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
        val context = holder.amount.context
        holder.amount.text = "+$%.2f".format(Math.abs(transaction.amount))
        holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green))
        holder.amountView.setImageResource(R.drawable.profits)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }
    fun setIncome(transactionList: List<Transaction>) {
        this.transactionList = transactionList
        notifyDataSetChanged()
    }
}