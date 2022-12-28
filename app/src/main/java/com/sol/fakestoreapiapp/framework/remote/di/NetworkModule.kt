package com.sol.fakestoreapiapp.framework.remote.di

import com.sol.fakestoreapiapp.framework.remote.services.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://fakestoreapi.com/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }


    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return okHttpClient.addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideNetworkService(retrofit:Retrofit) : NetworkService{
        return retrofit.create(NetworkService::class.java)
    }


}