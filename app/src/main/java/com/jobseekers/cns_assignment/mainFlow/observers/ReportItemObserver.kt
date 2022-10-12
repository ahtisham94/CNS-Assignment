package com.jobseekers.cns_assignment.mainFlow.observers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ReportItemObserver : BaseObservable() {


    var formid: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.formid)
        }
    var reportStatus: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.reportStatus)
        }
    var siteName: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.siteName)
        }
    var checkList: ArrayList<String>? = arrayListOf()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.checkList)
        }
    var createdBy: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.createdBy)
        }
    var details: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.details)
        }
    var noOfAttachments: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.noOfAttachments)
        }
    var location: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.location)
        }
}