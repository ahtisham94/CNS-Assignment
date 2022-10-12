package com.jobseekers.cns_assignment.coreBase

import com.jobseekers.cns_assignment.mainFlow.models.MainFlowResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetDataService {
    @GET("v3/{api_key_path}")
    suspend fun getReports(@Path("api_key_path") path: String): Response<ArrayList<MainFlowResponseModel>>
}