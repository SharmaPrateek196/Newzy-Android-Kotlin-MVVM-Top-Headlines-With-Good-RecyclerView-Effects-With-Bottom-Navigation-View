package com.example.newzy.ui.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newzy.repositories.HeadlinesRepository

// For getting instance of HeadlinesViewModel within the fragment
@Suppress("UNCHECKED_CAST")
class HeadlinesViewModelFactory (
        private val repository: HeadlinesRepository
    ) : ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HeadlinesViewModel(repository) as T
        }

}