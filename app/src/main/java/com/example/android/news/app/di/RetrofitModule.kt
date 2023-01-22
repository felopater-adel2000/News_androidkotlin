package com.example.android.news.app.di

import com.example.android.news.app.Constants
import com.example.android.news.data.retrofit.NewsServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule
{
    @Provides
    @Singleton
    fun provideNewsServiceAPI(): NewsServiceAPI
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        return retrofit.create(NewsServiceAPI::class.java)
    }
}