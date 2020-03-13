package br.com.radcloud.filmes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.radcloud.filmes.repository.MovieRepository

class MovieFavoritesViewModel(
    private val repository: MovieRepository
): ViewModel() {
    val favoriteMovies = repository.allFavorites().asLiveData()


}