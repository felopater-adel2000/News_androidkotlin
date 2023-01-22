package com.example.android.news.data

import com.example.android.news.domain.Article

data class Responce(
    val status: String?,
    val totalResults: Int?,
    val acticles: Array<Article>
)