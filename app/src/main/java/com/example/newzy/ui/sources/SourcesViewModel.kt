package com.example.newzy.ui.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newzy.dataObjects.Source
import com.example.newzy.repositories.SourcesRepository
import com.example.newzy.util.Coroutines
import kotlinx.coroutines.Job

/*
ViewModel sub-class for implementing MVVM pattern
for 'sources' endpoint
*/
class SourcesViewModel(
    private val sourcesRepository : SourcesRepository
) : ViewModel() {

    private lateinit var job: Job

    //Making private as it can be mutated
    private val _listOfSources = MutableLiveData<List<Source>>()
    //Keeping public as it is immutable
    val listOfSources : LiveData<List<Source>>
        get() = _listOfSources

    fun getSources() {
        job = Coroutines.ioThenMain(
            { sourcesRepository.getSources() },
            {
                _listOfSources.value = it?.sources
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}