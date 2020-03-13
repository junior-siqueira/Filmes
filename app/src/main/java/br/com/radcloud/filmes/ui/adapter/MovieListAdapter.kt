package br.com.radcloud.filmes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.radcloud.filmes.R
import br.com.radcloud.filmes.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(
    private val items: List<Movie>,
    private val onItemClick: (Movie) -> Unit
): RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieHolder(layout)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = items[position]
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2"+movie.poster_path).into(
            holder.imgCover
        )
        holder.txtTitle.text = movie.original_title
        holder.txtDate.text = movie.release_date
        holder.txtVote.text = movie.vote_average
        holder.itemView.setOnClickListener {
            onItemClick(movie)
        }
    }

    class MovieHolder(rootView: View): RecyclerView.ViewHolder(rootView) {
        val imgCover: ImageView = rootView.imgCover
        val txtTitle: TextView = rootView.txtTitle
        val txtDate: TextView = rootView.txtDate
        val txtVote: TextView = rootView.txtVote
    }

}