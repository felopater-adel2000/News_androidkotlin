package com.example.android.news.data.remote_data_source

import com.example.android.news.domain.Article

interface RemoteDataSource
{
    suspend fun getArticles(category: String): List<Article>

    suspend fun search(articleName: String): List<Article>
}