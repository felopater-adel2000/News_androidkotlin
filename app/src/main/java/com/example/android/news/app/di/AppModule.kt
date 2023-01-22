package com.example.android.news.app.di

import com.example.android.news.data.remote_data_source.RemoteDataSource
import com.example.android.news.data.remote_data_source.RetrofitRemoteDataSourceImp
import com.example.android.news.data.repo.RepositoryImp
import com.example.android.news.data.retrofit.NewsServiceAPI
import com.example.android.news.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import javax.sql.DataSource

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Provides
    @Singleton
    fun provideRemoteDataSource(api: NewsServiceAPI): RemoteDataSource
    {
        return RetrofitRemoteDataSourceImp(api)
    }

    @Provides
    @Singleton
    fun provideRepository(dataSource: RemoteDataSource): Repository
    {
        return RepositoryImp(dataSource)
    }
}