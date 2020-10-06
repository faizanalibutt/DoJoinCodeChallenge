package com.test.dojoincodetest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.dojoincodetest.R
import com.test.dojoincodetest.databinding.ActivityDetailBinding
import com.test.dojoincodetest.ui.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this@DetailActivity, R.layout.activity_detail)
        val viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(DetailViewModel::class.java)
        binding.detailModel = viewModel
        binding.lifecycleOwner = this

        viewModel.selectInPerson.observe(this, {
            if (it) viewModel.showAppointmentView.postValue(View.VISIBLE)
        })

        viewModel.selectBook.observe(this, {
            if (it) {
                viewModel.showService.postValue(View.VISIBLE)
                viewModel.showServiceType.postValue(DetailViewModel.Service.BOOK)
            }
        })

        viewModel.selectDrop.observe(this, {
            if (it) {
                viewModel.showService.postValue(View.VISIBLE)
                viewModel.showServiceType.postValue(DetailViewModel.Service.DROP)
            }
        })

        viewModel.backPress = {
            finish()
        }

    }
}