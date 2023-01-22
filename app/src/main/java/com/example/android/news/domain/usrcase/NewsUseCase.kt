package com.example.android.news.domain.usrcase

import com.example.android.news.domain.repo.Repository
import javax.inject.Inject

class NewsUseCase @Inject constructor(val repo: Repository)
{
    suspend fun scienceArticles() = repo.getScienceArticles()

    suspend fun sportsArticles() = repo.getSportsArticles()

    suspend fun businessArticles() = repo.getBusinessArticles()

    suspend fun technologyArticles() = repo.getTechnologyArticles()

    suspend fun searchForArticles(name: String) = repo.searchAbout(name)
}