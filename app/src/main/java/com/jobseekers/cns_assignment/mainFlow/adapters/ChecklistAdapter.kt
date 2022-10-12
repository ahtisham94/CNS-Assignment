package com.jobseekers.cns_assignment.mainFlow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobseekers.cns_assignment.databinding.ItemChecklistLayoutBinding
import com.jobseekers.cns_assignment.mainFlow.observers.ChecklistObserver

class ChecklistAdapter :
    ListAdapter<String, ChecklistAdapter.MyChecklistHolder>(ChecklistDiffUtil()) {
    private var list = mutableListOf<String>()

    inner class MyChecklistHolder(val binding: ItemChecklistLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            val observer = ChecklistObserver()
            observer.schedules = item
            binding.checkListObserver = observer
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyChecklistHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChecklistLayoutBinding.inflate(layoutInflater, parent, false)
        return MyChecklistHolder(binding)
    }

    override fun onBindViewHolder(holder: MyChecklistHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

    fun setList(list: MutableList<String>) {
        this.list = list
        submitList(list)
    }
}

class ChecklistDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ) = oldItem == newItem
}