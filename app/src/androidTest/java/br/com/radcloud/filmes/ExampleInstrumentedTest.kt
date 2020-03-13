package br.com.radcloud.filmes

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.radcloud.filmes.repository.AppDatabase
import br.com.radcloud.filmes.repository.Filme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val dao = db.getMovieDao()

        runBlocking {
            val movieUnderTest = Filme(
                "MEU_ID",
                "http://teste.com",
                "Meu Título",
                "7.7",
                "Alguma descrição",
                "2020-03-03"
            )

            val rowId = dao.save(movieUnderTest)
            assertTrue(rowId > -1)

            val movies = dao.allFavorites().first()
            movies.forEach {
                assertEquals(it.original_title, "Meu Título")
            }

            val isFavorite = dao.isFavorite("MEU_ID")
            assertTrue(isFavorite == 1)

            val result = dao.delete(movieUnderTest)
            assertTrue(result == 1)
        }
    }
}
