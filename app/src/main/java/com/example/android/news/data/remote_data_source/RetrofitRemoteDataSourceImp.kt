package com.example.android.news.data.remote_data_source

import com.example.android.news.app.Constants
import com.example.android.news.app.castJsonToArticles
import com.example.android.news.data.retrofit.NewsServiceAPI
import com.example.android.news.domain.Article
import org.json.JSONObject
import javax.inject.Inject

class RetrofitRemoteDataSourceImp @Inject constructor(val api: NewsServiceAPI) : RemoteDataSource
{
    override suspend fun getArticles(category: String): List<Article>
    {
        val responce = api.getArticlesByCategory(Constants.egCountry, category, Constants.arabicLanguage, Constants.apiKey)

        return castJsonToArticles(JSONObject(responce))
    }

    override suspend fun search(articleName: String): List<Article>
    {
        val responce = api.search(articleName, Constants.apiKey)
        return castJsonToArticles(JSONObject(responce))
    }
}