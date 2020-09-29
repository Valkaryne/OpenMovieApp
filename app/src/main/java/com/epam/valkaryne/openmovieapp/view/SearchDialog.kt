package com.epam.valkaryne.openmovieapp.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import com.epam.valkaryne.openmovieapp.R
import com.epam.valkaryne.openmovieapp.core.model.QueryModel
import com.epam.valkaryne.openmovieapp.databinding.DialogSearchBinding
import com.epam.valkaryne.openmovieapp.view.adapter.QueryHistoryAdapter

class SearchDialog(
    private val onDialogInteraction: OnDialogInteraction,
    private val queryHistory: LiveData<List<QueryModel>>
) : DialogFragment() {

    interface OnDialogInteraction {
        fun performSearch(queryModel: QueryModel)

        fun clearHistory()
    }

    private val binding: DialogSearchBinding by lazy {
        DialogSearchBinding.inflate(LayoutInflater.from(context))
    }

    private val movieTypes: Array<String> by lazy {
        resources.getStringArray(R.array.search_movies_types)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context).setView(binding.root).apply {
            val movieTypesAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item
            )
            movieTypesAdapter.addAll(*movieTypes)
            val historyAdapter = QueryHistoryAdapter(requireContext())

            with(binding) {
                searchType.adapter = movieTypesAdapter
                searchInput.setAdapter(historyAdapter)

                searchInput.setOnTouchListener { _, motionEvent ->
                    if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                        searchInput.showDropDown()
                    }
                    false
                }
                searchInput.setOnItemClickListener { adapterView, view, position, l ->
                    historyAdapter.getItem(position)?.let { item ->
                        if (item.type.isNotEmpty()) {
                            movieTypes.forEach { type ->
                                if (item.type == type) {
                                    searchType.setSelection(movieTypes.indexOf(type))
                                }
                            }
                        }
                        searchYear.setText(item.year)
                        searchInput.setText(item.title)
                    }
                }

                searchPerformButton.setOnClickListener {
                    dismiss()
                    onDialogInteraction.performSearch(assembleQueryModel())
                }

                searchClearButton.setOnClickListener {
                    onDialogInteraction.clearHistory()
                }

                queryHistory.observe(this@SearchDialog, {
                    historyAdapter.items = it
                    searchClearButton.isEnabled = it.isNotEmpty()
                })
            }
        }.create()
    }

    private fun assembleQueryModel(): QueryModel {
        val title = binding.searchInput.text.toString()
        val year = binding.searchYear.text.toString()
        val type = binding.searchType.selectedItem.toString()

        return QueryModel(
            title = title,
            year = year,
            type = if (type != movieTypes[0]) type else ""
        )
    }

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            onDialogInteraction: OnDialogInteraction,
            queryHistory: LiveData<List<QueryModel>>
        ) {
            SearchDialog(onDialogInteraction, queryHistory).show(fragmentManager, "search_dialog")
        }
    }
}