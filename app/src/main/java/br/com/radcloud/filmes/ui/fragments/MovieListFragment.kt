package br.com.radcloud.filmes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.radcloud.filmes.R
import br.com.radcloud.filmes.model.Movie
import br.com.radcloud.filmes.ui.MovieDetailActivity
import br.com.radcloud.filmes.ui.adapter.MovieListAdapter
import br.com.radcloud.filmes.ui.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment: Fragment() {
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is MovieListViewModel.State.Loading -> {
                    vwLoading.visibility = View.VISIBLE
                }
                is MovieListViewModel.State.Loaded -> {
                    vwLoading.visibility = View.GONE
                    recyclerView.adapter = MovieListAdapter(state.items, this::openMovieDetail)
                }
                is MovieListViewModel.State.Error -> {
                    vwLoading.visibility = View.GONE
                    if (!state.hasConsumed){
                        state.hasConsumed = true
                        Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_LONG).show()
                    }
                }
            }


        })
        viewModel.loadMovies()
    }

    private fun openMovieDetail(movie: Movie) {
        MovieDetailActivity.open(requireContext(), movie)
    }
}