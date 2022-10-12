package com.jobseekers.cns_assignment.mainFlow.di

import com.jobseekers.cns_assignment.coreBase.GetDataService
import com.jobseekers.cns_assignment.di.ApiKeyPath
import com.jobseekers.cns_assignment.mainFlow.domain.MainFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)

object MainFlowDi {
    @ActivityScoped
    @Provides
    fun dashboardRepo(dataService: GetDataService, @ApiKeyPath apiKey: String) =
        MainFlowUseCase(dataService, apiKey);
}