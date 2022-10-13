package com.jobseekers.cns_assignment.mainFlow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.jobseekers.cns_assignment.BR
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.coreBase.BaseFragment
import com.jobseekers.cns_assignment.databinding.FragmentMainBinding
import com.jobseekers.cns_assignment.mainFlow.adapters.ReportsAdapter
import com.jobseekers.cns_assignment.mainFlow.dialog.ReportDetailsDialog
import com.jobseekers.cns_assignment.mainFlow.models.ReportResponse
import com.jobseekers.cns_assignment.mainFlow.viewmodel.MainActivityViewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainActivityViewModel>() {

    val viewModel: MainActivityViewModel by activityViewModels()

    override fun getViewModels() = viewModel

    override fun getBindingVariable() = BR.mainFragment

    override fun getLayoutId() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObserver()
    }

    private fun setObserver() {
        getViewModels().clickListener().observe(viewLifecycleOwner) {
            if (it == R.id.startDateTv) {
                getDatePicker("Select Start Date") { date ->
                    getViewModels().mainFlowObserver.startDate = date
                }

            } else if (it == R.id.endDateTv) {
                getDatePicker("Select End Date") { date ->
                    getViewModels().mainFlowObserver.endDate = date
                }
            }
        }
    }

    private fun initViews() {
        getViewModels().getReports()
        binding.reportsRv.adapter = ReportsAdapter {
            ReportDetailsDialog(
                requireContext(),
                (it as ReportResponse).formid.toString(),
                it.details
            ).show()
        }
        binding.reportTypeTabLL.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                getViewModels().updateList(tab?.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }


}