package com.test.dojoincodetest.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.dojoincodetest.R
import com.test.dojoincodetest.model.SubCategories
import com.test.dojoincodetest.ui.activity.DetailActivity
import com.test.dojoincodetest.ui.activity.MainActivity

class SubCategoryListAdapter(val subCategoryList: List<SubCategories>) : RecyclerView.Adapter<SubCategoryListAdapter.ViewHolder1>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_sub_category, parent, false)
        return ViewHolder1(view)
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
        val category = subCategoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = subCategoryList.size

    class ViewHolder1(v: View) : RecyclerView.ViewHolder(v) {

        var view: View? = null
        init {
            this.view = v
        }
        fun bind(category: SubCategories) {
            view?.findViewById<TextView>(R.id.subcategoryTitle)?.text = category.title
            view?.findViewById<TextView>(R.id.subcategoryTitle)?.setOnClickListener {
                it.context.startActivity(
                    Intent(it.context as? MainActivity, DetailActivity::class.java)
                )
            }
        }

    }

}