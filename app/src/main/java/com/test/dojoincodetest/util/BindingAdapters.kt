package com.test.shaadoow.util

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.test.dojoincodetest.R
import com.test.dojoincodetest.ui.viewmodel.DetailViewModel
import com.test.shaadoow.util.extension.getParentActivity

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    parentActivity?.let {
        visibility?.observe(parentActivity, Observer { value ->
            view.visibility = value ?: View.GONE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    parentActivity?.let {
        text.observe(parentActivity, {value ->
            view.text = text.value ?: ""
        })
    }
}

@BindingAdapter("android:visibility")
fun setVisibility(view: ConstraintLayout, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    parentActivity?.let {
        visibility?.observe(parentActivity, { value ->
            view.visibility = value ?: View.GONE
        })
    }
}


@BindingAdapter("serviceType")
fun setServiceType(view: TextView, serviceType: MutableLiveData<DetailViewModel.Service>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    parentActivity?.let {
        serviceType?.observe(parentActivity, { service ->
            when (service) {
                DetailViewModel.Service.BOOK -> view.text =
                    parentActivity.getString(R.string.text_register)
                DetailViewModel.Service.DROP -> view.text =
                    parentActivity.getString(R.string.text_schedule)
                else -> {
                }
            }

        })
    }
}