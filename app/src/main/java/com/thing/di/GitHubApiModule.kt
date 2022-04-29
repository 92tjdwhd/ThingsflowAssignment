package com.thing.di

import com.thing.api.apiHelper.GitHubApiHelper
import com.thing.api.apiHelperImpl.GitHubApiHelperImpl
import com.thing.api.service.GitHubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GitHubApiModule {

    @Provides
    fun provideUrl() = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = run {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        OkHttpClient.Builder()
            .addInterceptor {
                val original: Request = it.request()
                val request: Request = original.newBuilder()
                    .build()
                it.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideStockRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(GitHubService::class.java)

    @Provides
    fun provideApiHelper(gitHubApiHelperImpl: GitHubApiHelperImpl): GitHubApiHelper = gitHubApiHelperImpl

}