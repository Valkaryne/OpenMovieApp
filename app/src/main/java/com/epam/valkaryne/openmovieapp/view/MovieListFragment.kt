package com.epam.valkaryne.openmovieapp.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
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

/**
 * Fragment that shows to user a list of movies which could be found by searching in OMDb API
 */
class MovieListFragment : Fragment() {

    private var searchJob: Job? = null

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: SearchMoviesViewModel by viewModel()
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.list.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.list.adapter = adapter

        configAdapter()

        setFragmentResultListener(SearchDialog.QUERY_MODEL_REQUEST_KEY) { _, bundle ->
            val result = bundle.get(SearchDialog.QUERY_MODEL_BUNDLE_KEY)
            if (result is QueryModel) {
                performSearch(result)
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        savedInstanceState?.getParcelable<QueryModel>(LAST_SEARCH_QUERY)?.let { restoredQuery ->
            performSearch(restoredQuery)
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

    private fun configAdapter() {
        adapter.addLoadStateListener { loadState ->
            binding.list.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            val errorState = loadState.source.refresh as? LoadState.Error
            errorState?.let {
                Toast.makeText(context, "${it.error.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showSearchDialog() {
        findNavController().navigate(R.id.action_movieListFragment_to_searchDialog)
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

    private fun performSearch(queryModel: QueryModel) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchMovies(queryModel).collectLatest { movies ->
                binding.promptTv.isVisible = false
                adapter.submitData(movies)
            }
        }
    }

    private companion object {
        const val LAST_SEARCH_QUERY = "last_search_query"
    }
}