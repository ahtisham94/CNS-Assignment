package com.jobseekers.cns_assignment

import com.jobseekers.cns_assignment.coreBase.GetDataService
import com.jobseekers.cns_assignment.mainFlow.domain.MainFlowUseCase
import com.jobseekers.cns_assignment.mainFlow.viewmodel.MainActivityViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Constant {
    const val BASEURL = "https://run.mocky.io/"
    const val API_KEY: String = "7280f61f-9c23-473e-8c1e-512505d7bcf1"


    private fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }
    private fun getService(): GetDataService {
        return getRetrofit().create(GetDataService::class.java)
    }

    private fun getMainFlowUseCase(): MainFlowUseCase{
        return MainFlowUseCase(getService(), API_KEY)
    }
    fun getMainActivityViewModel() : MainActivityViewModel{
        return MainActivityViewModel(getMainFlowUseCase())
    }
}