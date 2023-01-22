package com.example.android.news.app

import android.util.Log
import com.example.android.news.domain.Article
import com.example.android.news.domain.Source
import org.json.JSONObject
import java.util.LinkedList


fun castJsonToArticles(json: JSONObject): List<Article>
{
    //Log.i("Felo", " in cast function ${json.toString()}")
    if(json.getString("status") == "ok")
    {
        val result = LinkedList<Article>()
        val articlesArray = json.getJSONArray("articles")

        for(i in 0 until articlesArray.length())
        {
            val article = articlesArray[i] as JSONObject
            result.add(Article(
                source = Source(article.getJSONObject("source").getString("id"), article.getJSONObject("source").getString("name")),
                author = article.getString("author"),
                title = article.getString("title"),
                description = article.getString("description"),
                url = article.getString("url"),
                urlToImage = article.getString("urlToImage"),
                publishedAt = article.getString("publishedAt"),
                content = article.getString("content")
            ))
        }
        return result
    }
    else
    {
        return emptyList()
    }
}