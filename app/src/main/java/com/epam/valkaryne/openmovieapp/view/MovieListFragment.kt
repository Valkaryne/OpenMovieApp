package com.epam.valkaryne.openmovieapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.epam.valkaryne.openmovieapp.R
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.databinding.FragmentMovieListBinding
import com.epam.valkaryne.openmovieapp.view.adapter.MoviesAdapter
import com.epam.valkaryne.openmovieapp.vm.SearchMoviesViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private var searchJob: Job? = null

    private val viewModel: SearchMoviesViewModel by viewModel()
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.list.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.list.adapter = adapter

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.getParcelable<QueryModel>(LAST_SEARCH_QUERY)?.let { restoredQuery ->
            searchMovies(restoredQuery)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movie_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_movies -> {
                showSearchDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSearchDialog() {
        SearchDialog.show(
            parentFragmentManager,
            object : SearchDialog.OnDialogInteraction {
                override fun performSearch(queryModel: QueryModel) {
                    searchMovies(queryModel)
                    viewModel.saveQueryHistory(queryModel)
                }

                override fun clearHistory() {
                    viewModel.clearQueryHistory()
                }
            },
            viewModel.queryHistory
        )
    }

    private fun searchMovies(queryModel: QueryModel) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchMovies(queryModel).collectLatest { movies ->
                adapter.submitData(movies)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.lastQuery?.let {
            outState.putParcelable(LAST_SEARCH_QUERY, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchJob?.cancel()
    }

    private companion object {
        const val LAST_SEARCH_QUERY = "last_search_query"
    }
}