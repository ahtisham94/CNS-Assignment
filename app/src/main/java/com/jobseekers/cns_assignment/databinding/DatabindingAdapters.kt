package com.jobseekers.cns_assignment.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.extensions.loadImage
import com.jobseekers.cns_assignment.mainFlow.adapters.ChecklistAdapter
import com.jobseekers.cns_assignment.mainFlow.adapters.ReportsAdapter
import com.jobseekers.cns_assignment.mainFlow.models.ReportResponse

object DatabindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["loadImage"])
    fun loadImage(imageView: ImageView, url: Any = R.drawable.ic_avatar_svgrepo_com) {
        imageView.loadImage(url)
    }

    @JvmStatic
    @BindingAdapter(value = ["bindTab"])
    fun bindTabs(tabLayout: TabLayout, list: ArrayList<String>) {
        for (item in list) {
            tabLayout.addTab(tabLayout.newTab().setText(item))
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["reportStatusBackground"])
    fun reportStatusBackground(textView: AppCompatTextView, reportStatus: String) {
        if (reportStatus.trim() == "pending")
            textView.setBackgroundResource(R.drawable.rectang_red_yellow_gradiant_bg)
        else
            textView.setBackgroundResource(R.drawable.rectang_cyan_green_bg)

    }

    @JvmStatic
    @BindingAdapter(value = ["updateAdapter"])
    fun updateAdapter(recyclerView: RecyclerView?, list: ArrayList<ReportResponse>?) {
        if (recyclerView != null) {
            if (recyclerView.adapter != null) {
                if (list != null) {
                    (recyclerView.adapter as ReportsAdapter).setData(list.toMutableList())
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["updateCheckListAdapter"])
    fun updateCheckListAdapter(recyclerView: RecyclerView?, list: ArrayList<String>?) {
        if (recyclerView != null) {
            recyclerView.adapter = ChecklistAdapter()
            list?.toMutableList()?.let { (recyclerView.adapter as ChecklistAdapter).setList(it) }

        }
    }

    @JvmStatic
    @BindingAdapter(value = ["filter"])
    fun filter(recyclerView: RecyclerView?, filter: String?) {
        if (recyclerView != null) {
            if (recyclerView.adapter != null) {
                filter?.let { (recyclerView.adapter as ReportsAdapter).filter(it) }
            }
        }
    }

}