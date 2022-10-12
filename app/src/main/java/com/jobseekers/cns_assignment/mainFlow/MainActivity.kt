package com.jobseekers.cns_assignment.mainFlow

import android.os.Bundle
import androidx.activity.viewModels
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.coreBase.BaseActivity
import com.jobseekers.cns_assignment.databinding.ActivityMainBinding
import com.jobseekers.cns_assignment.mainFlow.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {
    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModels() = viewModel

    override fun getLayoutId() = R.layout.activity_main

}