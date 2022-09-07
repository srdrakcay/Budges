package com.serdar.budges.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.serdar.budges.adapter.CryptoAdapter
import com.serdar.budges.databinding.FragmentCurrencyBinding
import com.serdar.budges.di.repository.CryptoRepository
import com.serdar.budges.model.CryptoViewModel
import com.serdar.budges.model.CryptoViewModelFactory


class CurrencyFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyBinding
    private lateinit var viewModel: CryptoViewModel
    private val adapter by lazy { CryptoAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(layoutInflater)
        setupAdapter()
        val repository = CryptoRepository()
        val cryptoViewModelFactory = CryptoViewModelFactory(repository)
        try {
            viewModel = ViewModelProvider(this, cryptoViewModelFactory).get(CryptoViewModel::class.java)
            viewModel.getData()
            viewModel.myResponse.observe(requireActivity(), Observer {
                adapter.setData(it)

            })
        }catch (exception: Exception){
            Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun setupAdapter() {
        binding.crView.layoutManager = LinearLayoutManager(requireContext())
        binding.crView.adapter = adapter
    }


}