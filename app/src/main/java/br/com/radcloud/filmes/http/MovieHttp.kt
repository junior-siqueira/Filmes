package br.com.radcloud.filmes.http

import br.com.radcloud.filmes.model.SearchResult
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object MovieHttp {
    private const val API_KEY = "9c92917a355ab367b17804be259b44d3"
    private const val FILME_JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY"

    private val client = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    fun searchFilme(): SearchResult? {
        val request = Request.Builder()
            .url(String.format(FILME_JSON_URL))
            .build()
        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson<SearchResult>(
                json, SearchResult::class.java)
        } catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}