package com.example.newzy.ui.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newzy.repositories.SourcesRepository

// For getting instance of HeadlinesViewModel within the fragment
@Suppress("UNCHECKED_CAST")
class SourcesViewModelFactory (
    private val repository: SourcesRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SourcesViewModel(repository) as T
    }

}