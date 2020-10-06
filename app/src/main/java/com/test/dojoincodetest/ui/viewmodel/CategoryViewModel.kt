package com.test.dojoincodetest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.test.dojoincodetest.di.base.BaseViewModel
import com.test.dojoincodetest.model.Category

class CategoryViewModel : BaseViewModel() {

    private val categoryTitle = MutableLiveData<String>()

    fun bind(category: Category, res: Int) {
        categoryTitle.value = category.title
    }

    fun getCategoryTitle(): MutableLiveData<String> {
        return categoryTitle
    }

}