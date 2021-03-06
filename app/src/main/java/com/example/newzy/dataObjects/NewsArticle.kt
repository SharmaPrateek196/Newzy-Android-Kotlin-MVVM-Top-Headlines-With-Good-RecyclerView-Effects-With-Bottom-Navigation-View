package com.example.newzy.dataObjects

//POJO class for Every news article
//It comes inside the response of Top-Headline Request
data class NewsArticle (
    private var headlineSubSource: HeadlineSubSource?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
)