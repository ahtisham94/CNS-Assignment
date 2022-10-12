package com.jobseekers.cns_assignment.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.jobseekers.cns_assignment.BuildConfig
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.coreBase.GetDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Singleton
    @Provides
    fun getRetrofit(
        @BaseUrlQualifier baseUrl: String,
    ): Retrofit {

        val interceptor = getLogger()
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .client(okHttpClientBuilder.build())
            .build()

    }

    private fun getLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun getDataService(retrofit: Retrofit) = retrofit.create(GetDataService::class.java)

    @Singleton
    @Provides
    @BaseUrlQualifier
    fun getBaseUrl(@ApplicationContext context: Context) = context.getString(R.string.base_url)

    @Singleton
    @Provides
    @ApiKeyPath
    fun getApiPathKey(@ApplicationContext context: Context) = context.getString(R.string.api_key)
}