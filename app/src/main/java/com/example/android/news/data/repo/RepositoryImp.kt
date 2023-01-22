package com.example.android.news.data.repo

import com.example.android.news.app.Constants
import com.example.android.news.data.remote_data_source.RemoteDataSource
import com.example.android.news.domain.Article
import com.example.android.news.domain.repo.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(val dataSource: RemoteDataSource) : Repository {
    override suspend fun getSportsArticles(): List<Article>
    {
        return dataSource.getArticles(Constants.sportsCategory)
    }

    override suspend fun getScienceArticles(): List<Article>
    {
        return dataSource.getArticles(Constants.scienceCategory)
    }

    override suspend fun getBusinessArticles(): List<Article>
    {
        return dataSource.getArticles(Constants.businessCategory)
    }

    override suspend fun getTechnologyArticles(): List<Article>
    {
        return dataSource.getArticles(Constants.technologyCategory)
    }

    override suspend fun searchAbout(name: String): List<Article>
    {
        return dataSource.search(name)
    }
}