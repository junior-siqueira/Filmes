package br.com.radcloud.filmes.repository

import android.content.Context
import br.com.radcloud.filmes.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(context: Context) {
    private val dao: MovieDao = AppDatabase.getDatabase(context).getMovieDao()

    suspend fun save(movie: Movie) {
        dao.save(MovieFilmeMapper.movieToFilme(movie))
    }

    suspend fun delete(movie: Movie) {
        dao.delete(MovieFilmeMapper.movieToFilme(movie))
    }

    suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id) > 0
    }

    fun allFavorites(): Flow<List<Movie>> {
        return dao.allFavorites()
            .map {filmeList ->
                filmeList.map {filme ->
                    MovieFilmeMapper.filmeToMovie(filme)
                }
            }
    }
}