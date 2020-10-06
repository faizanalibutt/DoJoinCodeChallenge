package com.test.dojoincodetest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.SimpleItemAnimator
import com.test.dojoincodetest.R
import com.test.dojoincodetest.adapter.CategoryListAdapter
import com.test.dojoincodetest.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(MainViewModel::class.java)

        val adapter = CategoryListAdapter {
            viewModel.retry()
        }

        viewModel.categories.observe(this, {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.networkStateMain.observe(this, {
            adapter.setNetworkState(it)
        })

        (categoryListView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        categoryListView.adapter = adapter

    }
}