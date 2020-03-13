package br.com.radcloud.filmes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.radcloud.filmes.http.MovieHttp
import br.com.radcloud.filmes.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieListViewModel: ViewModel() {

    private val __state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = __state

    fun loadMovies() {
        if (__state.value != null) return

        viewModelScope.launch {
            __state.value = State.Loading
            val result = withContext(Dispatchers.IO) {
                MovieHttp.searchFilme()
            }
            if (result?.results == null){
                __state.value = State.Error(Exception("Erro ao listar os filmes"), false)
            }else {
                __state.value = State.Loaded(result?.results)
            }

        }
    }

    sealed class State {
        object Loading: State()
        data class Loaded(val items: List<Movie>): State()
        data class Error(val e: Throwable, var hasConsumed: Boolean): State()
    }
}