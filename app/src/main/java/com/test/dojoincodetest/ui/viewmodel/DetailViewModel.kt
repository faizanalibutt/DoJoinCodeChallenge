package com.test.dojoincodetest.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel() : ViewModel() {

    val selectInPerson = MutableLiveData<Boolean>()
    val showAppointmentView = MutableLiveData<Int>()
    lateinit var backPress: () -> Unit
    val selectBook = MutableLiveData<Boolean>()
    val selectDrop = MutableLiveData<Boolean>()
    val showService = MutableLiveData<Int>()
    val showServiceType = MutableLiveData<Service>()

    init {
        selectInPerson.value = false
        showAppointmentView.value = View.GONE
        selectBook.value = false
        selectDrop.value = false
        showService.value = View.GONE
        showServiceType.value = Service.NONE
    }

    fun selectInPerson() {
        selectInPerson.value = true
    }

    fun bookMe() {
        selectBook.value = true
    }

    fun dropMe() {
        selectDrop.value = true
    }

    fun backToMain() {
        backPress.invoke()
    }

    fun nextPage() {}

    enum class Service {
        BOOK,
        DROP,
        NONE
    }

}