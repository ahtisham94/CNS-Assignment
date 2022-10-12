package com.jobseekers.cns_assignment.mainFlow.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReportResponse(
    @SerializedName("formid") val formid: Int,
    @SerializedName("date") val date: String,
    @SerializedName("createdBy") val createdBy: String,
    @SerializedName("reportStatus") val reportStatus: String,
    @SerializedName("createdByImage") val createdByImage: String,
    @SerializedName("location") val location: String,
    @SerializedName("details") val details: String,
    @SerializedName("siteName") val siteName: String,
    @SerializedName("checklists") val checklists: ArrayList<String>,
    @SerializedName("attachments") val attachments: ArrayList<String>,

    ) : Parcelable