package com.example.newzy.dataObjects

//POJO class for response from 'top-headline' api endpoint
data class HeadlinesResponse (
        var status: String?,
        var totalResults: Int?,
        var articles: List<NewsArticle>?
)