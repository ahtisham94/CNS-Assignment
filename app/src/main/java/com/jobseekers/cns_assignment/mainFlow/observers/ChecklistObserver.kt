package com.jobseekers.cns_assignment.mainFlow.observers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ChecklistObserver : BaseObservable() {

    var schedules: String? = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.schedules)
        }
}