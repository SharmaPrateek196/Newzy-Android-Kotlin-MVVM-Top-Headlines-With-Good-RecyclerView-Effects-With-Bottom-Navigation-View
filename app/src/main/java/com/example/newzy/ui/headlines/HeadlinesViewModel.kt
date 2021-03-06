package com.example.newzy.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newzy.dataObjects.NewsArticle
import com.example.newzy.repositories.HeadlinesRepository
import com.example.newzy.util.Coroutines
import kotlinx.coroutines.Job

/*
ViewModel sub-class for implementing MVVM pattern
for 'top-headlines' endpoint
*/
class HeadlinesViewModel(
    private val headlinesRepository : HeadlinesRepository
) : ViewModel() {

    private lateinit var job: Job

    //Making private as it is mutable
    private val _listOfArticles = MutableLiveData<List<NewsArticle>>()
    //Keeping public as it is immutable
    val listOfArticles : LiveData<List<NewsArticle>>
        get() = _listOfArticles

    fun getHeadlines() {
        job = Coroutines.ioThenMain(
            { headlinesRepository.getHeadlines() },
            {
                _listOfArticles.value = it?.articles
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}