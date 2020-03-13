package br.com.radcloud.filmes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.radcloud.filmes.model.Movie
import br.com.radcloud.filmes.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: MovieRepository
): ViewModel() {
    private val __isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = __isFavorite

    fun onCreate(movie: Movie) {
        viewModelScope.launch {
            __isFavorite.value = repository.isFavorite(movie.id)
        }
    }

    fun saveToFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.save(movie)
            __isFavorite.value = repository.isFavorite(movie.id)
        }
    }

    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.delete(movie)
            __isFavorite.value = repository.isFavorite(movie.id)
        }
    }
}