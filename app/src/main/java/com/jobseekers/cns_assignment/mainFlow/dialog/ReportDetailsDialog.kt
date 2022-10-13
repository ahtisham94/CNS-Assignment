package com.jobseekers.cns_assignment.mainFlow.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.databinding.DialogReportDetailsLayoutBinding

@SuppressLint("SetTextI18n")
class ReportDetailsDialog(context: Context, tid: String, reportDes: String) :
    Dialog(context, R.style.SuccessDialog) {
    var binding: DialogReportDetailsLayoutBinding? = null

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        binding = DialogReportDetailsLayoutBinding.inflate(LayoutInflater.from(getContext()))
        setContentView(binding!!.root)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = ((context.resources.displayMetrics.widthPixels * 0.90).toInt());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.y = 100
        window!!.attributes = lp
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        window!!.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )
        window!!.attributes.gravity = Gravity.BOTTOM
        binding?.reportTv?.text =
            String.format(getContext().resources.getString(R.string.report_id_), tid)
        binding?.reportIdValueTv?.text = reportDes

    }
}