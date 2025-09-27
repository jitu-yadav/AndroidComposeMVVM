package com.tweetsy.composedemoapp.di

import com.tweetsy.composedemoapp.api.TweetsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideTweetsyAPI(retrofit: Retrofit): TweetsAPI {
        return retrofit.create(TweetsAPI::class.java)
    }
}