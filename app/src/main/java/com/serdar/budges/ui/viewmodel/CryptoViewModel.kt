package com.serdar.budges.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serdar.budges.data.crypto.Data
import com.serdar.budges.di.repository.CryptoRepository
import kotlinx.coroutines.launch

class CryptoViewModel(private val cryptoRepository: CryptoRepository) : ViewModel() {

    val myResponse: MutableLiveData<List<Data>> = MutableLiveData()

    fun getData() {
        viewModelScope.launch {
            val response = cryptoRepository.getPost()
            myResponse.value = response
        }
    }
}