package com.example.newzy.dataObjects

//POJO class for response from sources api endpoint
data class SourcesResponse (
        var status: String?,
        var sources: List<Source>?
)