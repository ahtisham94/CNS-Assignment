package com.jobseekers.cns_assignment.mainFlow.domain

import com.jobseekers.cns_assignment.coreBase.BaseUseCase
import com.jobseekers.cns_assignment.coreBase.GetDataService
import com.jobseekers.cns_assignment.di.ApiKeyPath
import javax.inject.Inject

class MainFlowUseCase @Inject constructor(
    private val apiService: GetDataService,
    @ApiKeyPath val path: String
) :
    BaseUseCase() {

    /**
     * This method get report list from API
     */
    suspend fun getReports() = baseApiResultHandler() {
        apiService.getReports(path)
    }
}