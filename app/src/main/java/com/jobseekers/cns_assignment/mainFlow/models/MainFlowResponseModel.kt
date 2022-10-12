package com.jobseekers.cns_assignment.mainFlow.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainFlowResponseModel(
    @SerializedName("notificationCount") val notificationCount: String,
    @SerializedName("inbox") val inbox: ArrayList<ReportResponse>,
    @SerializedName("outbox") val outbox: ArrayList<ReportResponse>,
    @SerializedName("archived") val archived: ArrayList<ReportResponse>,
) :
    Parcelable