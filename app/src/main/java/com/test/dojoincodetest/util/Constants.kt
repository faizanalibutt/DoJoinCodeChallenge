package com.test.shaadoow.util

import androidx.recyclerview.widget.DiffUtil
import com.test.dojoincodetest.model.SubCategories

object Constants {

    /** The base URL of the APIs */
    const val BASE_URL = "http://208.109.13.111:9090"
    const val CATEGORY_END_POINT = "/api/Category"

    val DIFF_CALL: DiffUtil.ItemCallback<SubCategories> = object : DiffUtil.ItemCallback<SubCategories>() {
        override fun areItemsTheSame(oldItem: SubCategories, newItem: SubCategories): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SubCategories, newItem: SubCategories): Boolean {
            return oldItem == newItem
        }
    }
}