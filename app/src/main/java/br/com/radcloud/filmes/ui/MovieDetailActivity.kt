package br.com.radcloud.filmes.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.radcloud.filmes.R
import br.com.radcloud.filmes.model.Movie
import br.com.radcloud.filmes.repository.MovieRepository
import br.com.radcloud.filmes.ui.viewmodel.MovieDetailViewModel
import br.com.radcloud.filmes.ui.viewmodel.MovieFavoritesViewModel
import br.com.radcloud.filmes.ui.viewmodel.MovieVmFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(this,
            MovieVmFactory(
                MovieRepository(this)
            )
        ).get(MovieDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null){
            Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2"+movie.poster_path).into(
                imgCover
            )
            txtTitle.text = movie.original_title
            txtDate.text = movie.release_date
            txtVote.text = movie.vote_average
            txtOverview.text = movie.overview

            viewModel.isFavorite.observe(
                this,
                Observer { isFavorite ->
                    if (isFavorite) {
                        fabFavorite.setImageResource(R.drawable.ic_delete)
                        fabFavorite.setOnClickListener{
                            viewModel.removeFromFavorites(movie)
                        }
                    } else {
                        fabFavorite.setImageResource(R.drawable.ic_add)
                        fabFavorite.setOnClickListener{
                            viewModel.saveToFavorites(movie)
                        }
                    }
                }
            )
            viewModel.onCreate(movie)
        } else {
            finish()
        }
    }

    companion object {
        private const val EXTRA_MOVIE = "movie"
        fun open(context: Context, movie: Movie) {
            val detalIntent = Intent(context, MovieDetailActivity::class.java)
            detalIntent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(detalIntent)
        }
    }
}
