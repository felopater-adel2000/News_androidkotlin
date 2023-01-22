package com.example.android.news.data.retrofit

import com.example.android.news.data.Responce
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceAPI
{
    @GET("top-headlines")
    suspend fun getArticlesByCategory(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): String

    @GET("everything")
    suspend fun search(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): String
}