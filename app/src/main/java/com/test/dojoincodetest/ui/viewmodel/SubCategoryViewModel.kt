package com.test.dojoincodetest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.test.dojoincodetest.di.base.BaseViewModel
import com.test.dojoincodetest.model.SubCategories

class SubCategoryViewModel : BaseViewModel() {

    private val subcategoryTitle = MutableLiveData<String>()

    fun bind(category: SubCategories, res: Int) {
        subcategoryTitle.value = category.title
    }

    fun getSubCategoryTitle(): MutableLiveData<String> {
        return subcategoryTitle
    }

}