package br.com.radcloud.filmes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.radcloud.filmes.R
import br.com.radcloud.filmes.model.Movie
import br.com.radcloud.filmes.repository.MovieRepository
import br.com.radcloud.filmes.ui.MovieDetailActivity
import br.com.radcloud.filmes.ui.adapter.MovieListAdapter
import br.com.radcloud.filmes.ui.viewmodel.MovieFavoritesViewModel
import br.com.radcloud.filmes.ui.viewmodel.MovieVmFactory
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieFavoritesFragment: Fragment() {
    private val viewModel: MovieFavoritesViewModel by lazy {
        ViewModelProvider(this,
            MovieVmFactory(
                MovieRepository(requireContext())
            )
        ).get(MovieFavoritesViewModel::class.java)
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
        viewModel.favoriteMovies.observe(viewLifecycleOwner, Observer { items ->
            vwLoading.visibility = View.GONE
            recyclerView.adapter = MovieListAdapter(items, this::openMovieDetail)
        })
    }

    private fun openMovieDetail(movie: Movie) {
        MovieDetailActivity.open(requireContext(), movie)
    }
}