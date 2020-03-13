package br.com.radcloud.filmes

import br.com.radcloud.filmes.http.MovieHttp
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun searchIsComplete() {
        val searchResult = MovieHttp.searchFilme()
        searchResult?.results?.forEach {
            println(it.original_title + " - " + it.vote_average + " - " + it.poster_path + " - " + it.release_date)
        }
    }
}
