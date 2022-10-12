package com.jobseekers.cns_assignment.coreBase

import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.dialogs.MyProgressDialog
import com.jobseekers.cns_assignment.dialogs.SuccessDialog
import kotlinx.coroutines.launch


abstract class BaseActivity<Binding : ViewDataBinding, V : ViewModel?> : AppCompatActivity() {

    protected var binding: Binding? = null

    private var progressDialog: MyProgressDialog? = null

    private var baseViewmodel: V? = null

    abstract fun getViewModels(): V

    @LayoutRes
    abstract fun getLayoutId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(getLayoutId())
        baseViewmodel = baseViewmodel ?: getViewModels()
        progressDialog = MyProgressDialog(this)
        setObserver()
    }

    private fun bindView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)

    }

    private fun setObserver() {
        if (baseViewmodel is BaseViewmodel) {

            lifecycleScope.launch {
                (baseViewmodel as BaseViewmodel).apiFlow.collect {itemType ->
                    when (itemType) {
                        is APIState.ShowHideDialog -> {
                            if (itemType.showHide) progressDialog?.show() else progressDialog?.hide()
                        }
                        is APIState.Error -> {
                            showSuccessDialog(
                                "Alert",
                                itemType.errorMessage,
                                R.drawable.ic_alert_svgrepo_com,
                                null
                            )
                        }
                        else -> {

                        }
                    }
                }
            }

        }


    }

    open fun onDismiss(param: Any?) {

    }

    private fun showSuccessDialog(
        code: String,
        message: String, icon: Int = R.drawable.ic_circle_green_updated,
        res: Any? = null

    ) {
        val successErrorDialog = SuccessDialog(
            this,
            code,
            message, icon, res
        ) {
            onDismiss(it)
        }
        successErrorDialog.show()
    }




}