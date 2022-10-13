package com.jobseekers.cns_assignment.mainFlow.observers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.jobseekers.cns_assignment.mainFlow.models.ReportResponse

class MainFlowObserver : BaseObservable() {

    var notification: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.notification)
        }

    var startDate: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.startDate)
        }

    var endDate: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.endDate)
        }

    var reportTabList: ArrayList<String>? = arrayListOf()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.reportTabList)
        }

    var reportList: ArrayList<ReportResponse>? = arrayListOf()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.reportList)
        }

    var searchReport: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.searchReport)
        }

    fun onTextChanged(
        s: CharSequence
    ) {
        searchReport = s.toString()
    }
}