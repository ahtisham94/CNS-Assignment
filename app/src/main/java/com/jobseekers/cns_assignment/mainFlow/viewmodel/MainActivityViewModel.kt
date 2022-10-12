package com.jobseekers.cns_assignment.mainFlow.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jobseekers.cns_assignment.coreBase.APIState
import com.jobseekers.cns_assignment.coreBase.BaseViewmodel
import com.jobseekers.cns_assignment.mainFlow.domain.MainFlowUseCase
import com.jobseekers.cns_assignment.mainFlow.models.MainFlowResponseModel
import com.jobseekers.cns_assignment.mainFlow.models.ReportResponse
import com.jobseekers.cns_assignment.mainFlow.observers.MainFlowObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainFlowUseCase: MainFlowUseCase) :
    BaseViewmodel() {

    var mainFlowObserver = MainFlowObserver()
    lateinit var response: ArrayList<MainFlowResponseModel>
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            apiChannel.send(APIState.Error(exception.message ?: "Unknown exception"))
        }
    }

    /**
     * This method get from data layer and update models
     */
    fun getReports() {
        viewModelScope.launch(exceptionHandler) {
            mainFlowUseCase.getReports().collect {
                when (it) {
                    is APIState.NetworkResponseSuccess -> {
                        Log.i("resReports", it.response.toString())
                        response = it.response as ArrayList<MainFlowResponseModel>
                        setMainObserver(response[0])

                    }
                    is APIState.Error -> {
                        apiChannel.send(it)

                    }
                    is APIState.ShowHideDialog -> {
                        apiChannel.send(it)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun setMainObserver(response: MainFlowResponseModel) {
        mainFlowObserver.notification = response.notificationCount
        val tabList = arrayListOf<String>()
        tabList.add("Inbox (${response.inbox.size})")
        tabList.add("Outbox (${response.outbox.size})")
        tabList.add("Archives (${response.archived.size})")
        mainFlowObserver.reportTabList = tabList
        mainFlowObserver.reportList = response.inbox
    }

    fun updateList(position: Int?) {
        if (response != null) {
            when (position) {
                0 -> {
                    mainFlowObserver.reportList = response[0].inbox
                }
                1 -> {
                    mainFlowObserver.reportList =
                        response[0].outbox
                }
                2 -> {
                    mainFlowObserver.reportList = response[0].archived
                }
            }
        }
    }
}