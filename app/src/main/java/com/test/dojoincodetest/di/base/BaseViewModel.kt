package com.test.dojoincodetest.di.base

import androidx.lifecycle.ViewModel
import com.test.dojoincodetest.di.component.DaggerViewModelInjector
import com.test.dojoincodetest.ui.viewmodel.CategoryViewModel
import com.test.dojoincodetest.ui.viewmodel.NetworkStateViewModel
import com.test.dojoincodetest.ui.viewmodel.SubCategoryViewModel
import com.test.dojoincodetest.di.component.ViewModelInjector
import com.test.dojoincodetest.di.module.NetworkModule
import com.test.dojoincodetest.ui.viewmodel.MainViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CategoryViewModel -> injector.inject(this)
            is NetworkStateViewModel -> injector.inject(this)
            is SubCategoryViewModel -> injector.inject(this)
            is MainViewModel -> injector.inject(this)
        }
    }
}