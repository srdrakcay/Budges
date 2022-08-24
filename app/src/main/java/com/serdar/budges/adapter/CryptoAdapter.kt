package com.serdar.budges.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serdar.budges.R
import com.serdar.budges.data.crypto.Data
import java.math.RoundingMode

class CryptoAdapter:RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    private var myList = emptyList<Data>()

    inner class CryptoViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val symbol = itemview.findViewById<TextView>(R.id.symbol)
        val price = itemview.findViewById<TextView>(R.id.price)
        val macap = itemview.findViewById<TextView>(R.id.maCap)
        val name = itemview.findViewById<TextView>(R.id.currency)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.crypto_layout,parent,false))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.symbol.text=myList[position].symbol.toString()
        holder.name.text=myList[position].name.toString()
        holder.price.text=myList[position].priceUsd.toString()
        holder.price.text=myList[position].priceUsd.toBigDecimal().setScale(1, RoundingMode.UP).toString()
        holder.macap.text=myList[position].marketCapUsd.toBigDecimal().setScale(1, RoundingMode.UP).toString()

    }

    override fun getItemCount(): Int {
        return myList.size
    }
    fun setData(newList: List<Data>){
        myList= newList
        notifyDataSetChanged()

    }
}