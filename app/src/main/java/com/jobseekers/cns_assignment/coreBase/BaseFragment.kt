package com.jobseekers.cns_assignment.coreBase

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.*
import com.jobseekers.cns_assignment.R
import com.jobseekers.cns_assignment.dialogs.SuccessDialog
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseFragment<Binding : ViewDataBinding, V : ViewModel> : Fragment() {

    private var mViewModel: V? = null

    lateinit var binding: Binding

    abstract fun getViewModels(): V

    abstract fun getBindingVariable(): Int

    lateinit var mBaseActivity: BaseActivity<ViewDataBinding, ViewModel>

    private val datePickerBuilder = MaterialDatePicker.Builder.datePicker()

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::binding.isInitialized.not()) {
            binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireActivity() is BaseActivity<*, *>)
            mBaseActivity = requireActivity() as BaseActivity<ViewDataBinding, ViewModel>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModels()
        binding.setVariable(getBindingVariable(), mViewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

    }


    /**
     * This function will be used in future if we want to handle specific codes
     * and update UI accordingly
     */
    open fun onDismiss(params: Any?) {
    }


    fun showSuccessDialog(
        code: String,
        message: String, icon: Int = R.drawable.ic_circle_green_updated,
        res: Any? = null

    ) {
        val successErrorDialog = SuccessDialog(
            requireContext(),
            code,
            message, icon, res
        ) {
            onDismiss(it)
        }
        successErrorDialog.show()
    }


    /**
     * This function is used to close keyboard
     */
    fun closeKeyboard() {
        val inputManager =
            requireActivity().applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (requireActivity().currentFocus != null) inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus!!.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun getDatePicker(
        title: String,
        block: (s: String) -> Unit
    ) {
        datePickerBuilder.apply {
            setTheme(R.style.MaterialCalendarTheme)
            setTitleText(title)
            setPositiveButtonText("Done")
            setNegativeButtonText("Cancel")
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now())
        }
        val dialog = datePickerBuilder.build()
        dialog.show(childFragmentManager, "")
        dialog.apply {
            addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
                    block.invoke(dateFormatter.format(Date(it)))
                    dismiss()
                }
                addOnNegativeButtonClickListener {
                    dismiss()
                }
            }

        }

    }