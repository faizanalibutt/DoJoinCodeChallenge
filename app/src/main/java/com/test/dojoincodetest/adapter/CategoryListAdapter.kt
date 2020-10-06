package com.test.dojoincodetest.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.dojoincodetest.R
import com.test.dojoincodetest.databinding.ItemCategoryBinding
import com.test.dojoincodetest.databinding.ItemNetworkStateBinding
import com.test.dojoincodetest.model.Category
import com.test.dojoincodetest.ui.activity.DetailActivity
import com.test.dojoincodetest.ui.activity.MainActivity
import com.test.dojoincodetest.ui.viewmodel.CategoryViewModel
import com.test.dojoincodetest.ui.viewmodel.NetworkStateViewModel
import com.test.dojoincodetest.util.NetworkState

class CategoryListAdapter(private val retryCallback: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_PROGRESS = 0
    private val TYPE_ITEM = 1
    var onItemClick: ((Category, Int) -> Unit)? = { category, position ->
        val expanded: Boolean = category.expand
        category.expand = !expanded
        notifyItemChanged(position)
    }
    private var mCategoryList: List<Category>? = null

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_ITEM -> {
                val binding: ItemCategoryBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),
                    R.layout.item_category, parent, false
                )
                ViewHolder1(binding)
            }
            TYPE_PROGRESS -> {
                val binding: ItemNetworkStateBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),
                    R.layout.item_network_state, parent, false
                )
                ViewHolder2(binding, retryCallback)
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder1) {
            mCategoryList?.let { holder.bind(mCategoryList!![position], position) }
        } else if (holder is ViewHolder2) {
            holder.bind(networkState)
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return mCategoryList?.let { it.size + if (hasExtraRow()) 1 else 0 } ?: if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    inner class ViewHolder1(private val binding: ItemCategoryBinding)
        : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CategoryViewModel()
        fun bind(category: Category, position: Int) {
            viewModel.bind(category, position)
            binding.categoryTitle.setOnClickListener {
                onItemClick?.invoke(category, position)
            }
            val expanded: Boolean = category.expand
            binding.subCategoryListView.visibility = if (expanded) View.VISIBLE else View.GONE
            val adapter = SubCategoryListAdapter(category.subCategories)
            binding.subCategoryListView.adapter = adapter
            binding.categoryViewModel = viewModel
        }
    }

    internal class ViewHolder2(
        private val binding: ItemNetworkStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = NetworkStateViewModel()
        init {
            binding.retry.setOnClickListener {
                retryCallback.invoke()
            }
        }
        fun bind(networkState: NetworkState?) {
            viewModel.bindView(networkState)
            binding.viewModel = viewModel
        }
    }

    fun submitList(updatedList: List<Category>) {
        mCategoryList = updatedList

    }

}