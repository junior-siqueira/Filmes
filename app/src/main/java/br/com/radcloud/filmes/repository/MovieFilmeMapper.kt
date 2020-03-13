package br.com.radcloud.filmes.repository

import br.com.radcloud.filmes.model.Movie

object MovieFilmeMapper {
    fun filmeToMovie(filme: Filme) =
        Movie(
            filme.id,
            filme.poster_path,
            filme.original_title,
            filme.vote_average,
            filme.overview,
            filme.release_date
    )

    fun movieToFilme (movie: Movie) =
        movie.run {
            Filme(
                id,
                poster_path,
                original_title,
                vote_average,
                overview,
                release_date
            )
        }

}