package com.test.dojoincodetest.di.component

import com.test.dojoincodetest.ui.viewmodel.CategoryViewModel
import com.test.dojoincodetest.ui.viewmodel.NetworkStateViewModel
import com.test.dojoincodetest.ui.viewmodel.SubCategoryViewModel
import com.test.dojoincodetest.di.module.NetworkModule
import com.test.dojoincodetest.ui.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(categoryViewModel: CategoryViewModel)
    fun inject(subCategoryViewModel: SubCategoryViewModel)
    fun inject(networkStateViewModel: NetworkStateViewModel)
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}