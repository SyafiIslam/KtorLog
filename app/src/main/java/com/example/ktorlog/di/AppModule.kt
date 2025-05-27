package com.example.ktorlog.di

import com.example.ktorlog.data.remote.ApiConfig
import com.example.ktorlog.data.repository.AccountRepository
import com.example.ktorlog.data.repository.MovieRepository
import com.example.ktorlog.data.repository.SearchRepository
import com.example.ktorlog.data.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApi()= ApiConfig.ktorClient

    @Provides
    @Singleton
    fun provideMovieRepository(api: HttpClient)= MovieRepository(api)

    @Provides
    @Singleton
    fun provideTvRepository(api: HttpClient)= TvRepository(api)

    @Provides
    @Singleton
    fun provideAccountRepository(api: HttpClient)= AccountRepository(api)

    @Provides
    @Singleton
    fun provideSearchRepository(api: HttpClient)= SearchRepository(api)
}