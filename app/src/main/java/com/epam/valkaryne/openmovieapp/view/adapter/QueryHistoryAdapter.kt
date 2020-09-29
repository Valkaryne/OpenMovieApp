package com.epam.valkaryne.openmovieapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.databinding.QueryItemBinding

class QueryHistoryAdapter(private val context: Context) : BaseAdapter(), Filterable {

    var items: List<QueryModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): QueryModel = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val binding = if (view != null) {
            checkNotNull(DataBindingUtil.getBinding(view))
        } else QueryItemBinding.inflate(
            LayoutInflater.from(context), viewGroup, false
        )
        binding.query = items[position]
        return binding.root
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults? {
            return null
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {}
    }
}