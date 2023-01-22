package com.example.android.news.domain.repo

import com.example.android.news.domain.Article

interface Repository
{
    suspend fun getSportsArticles(): List<Article>

    suspend fun getScienceArticles(): List<Article>

    suspend fun getBusinessArticles(): List<Article>

    suspend fun getTechnologyArticles(): List<Article>

    suspend fun searchAbout(name: String): List<Article>
}