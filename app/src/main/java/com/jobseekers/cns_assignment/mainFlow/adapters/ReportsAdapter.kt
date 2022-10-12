package com.jobseekers.cns_assignment.mainFlow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.databinding.ItemReportLayoutBinding
import com.jobseekers.cns_assignment.extensions.setOnSingleClickListener
import com.jobseekers.cns_assignment.mainFlow.FilterCallback
import com.jobseekers.cns_assignment.mainFlow.models.ReportResponse
import com.jobseekers.cns_assignment.mainFlow.observers.ReportItemObserver

class ReportsAdapter(val block: (any: Any) -> Unit) :
    ListAdapter<ReportResponse, ReportsAdapter.MyReportsHolder>(ReportDiffUtils()), FilterCallback {
    private var list = mutableListOf<ReportResponse>()

    inner class MyReportsHolder(val binding: ItemReportLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReportResponse) {
            val observer = ReportItemObserver()
            observer.checkList = item.checklists
            observer.createdBy = item.createdBy
            observer.details = item.details
            observer.formid = String.format(
                binding.root.context.getString(R.string.report_id),
                item.formid
            )
            observer.noOfAttachments = String.format(
                binding.root.context.getString(R.string.no_files),
                item.attachments.size.toString()
            )
            observer.location = item.location
            observer.siteName = item.siteName
            observer.reportStatus = item.reportStatus
            binding.root.setOnSingleClickListener {
                block.invoke(item)
            }
            binding.reportItemObserver = observer
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReportsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReportLayoutBinding.inflate(layoutInflater, parent, false)
        return MyReportsHolder(binding)
    }

    override fun onBindViewHolder(holder: MyReportsHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

    override fun filter(filter: String) {
        if (filter.isNotEmpty()) {
            val tempList = list.filter {
                it.reportStatus.contains(filter, true)
            }
            submitList(tempList)
        } else submitList(list)
    }


    fun setData(list: MutableList<ReportResponse>) {
        this.list = list
        submitList(list)
    }

}


class ReportDiffUtils : DiffUtil.ItemCallback<ReportResponse>() {
    override fun areItemsTheSame(oldItem: ReportResponse, newItem: ReportResponse) =
        oldItem.formid == newItem.formid

    override fun areContentsTheSame(
        oldItem: ReportResponse,
        newItem: ReportResponse
    ) = oldItem == newItem
}