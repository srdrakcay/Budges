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

class HomeCryptoAdapter : RecyclerView.Adapter<HomeCryptoAdapter.HomeViewHolder>() {

    private var dataList = emptyList<Data>()

    inner class HomeViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val symbol = itemview.findViewById<TextView>(R.id.name)
        val price = itemview.findViewById<TextView>(R.id.priceUs)
        val symbolView = itemview.findViewById<ImageView>(R.id.symbolview)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.crypto_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.symbol.text = dataList[position].symbol.toString()
        holder.price.text = dataList[position].priceUsd.toString()
        holder.price.text =
            dataList[position].priceUsd.toBigDecimal().setScale(1, RoundingMode.UP).toString()

        when (dataList[position].symbol) {


            "BTC" -> holder.symbolView.setImageResource(R.drawable.bitcoin)
            "ETH" -> holder.symbolView.setImageResource(R.drawable.ethereum)
            "USDT" -> holder.symbolView.setImageResource(R.drawable.tether)
            "USDC" -> holder.symbolView.setImageResource(R.drawable.usdc)
            "BNB" -> holder.symbolView.setImageResource(R.drawable.bnb)
            "BUSD" -> holder.symbolView.setImageResource(R.drawable.busd)
            "ADA" -> holder.symbolView.setImageResource(R.drawable.cardano)
            "XRP" -> holder.symbolView.setImageResource(R.drawable.xrp)
            "SOL" -> holder.symbolView.setImageResource(R.drawable.solana)
            "DOT" -> holder.symbolView.setImageResource(R.drawable.polkadot)


        }
    }

    override fun getItemCount(): Int {
        if (dataList.size > 10) {
            return 10
        }
        return dataList.size
    }

    fun setDatas(newList: List<Data>) {
        dataList = newList
        notifyDataSetChanged()

    }
}