package com.serdar.budges.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.serdar.budges.di.repository.CryptoRepository

class CryptoViewModelFactory(
    private val repository: CryptoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CryptoViewModel(repository) as T
    }

}
