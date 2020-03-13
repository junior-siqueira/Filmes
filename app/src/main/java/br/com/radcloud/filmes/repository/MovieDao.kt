package br.com.radcloud.filmes.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(filme: Filme): Long

    @Delete
    suspend fun delete(vararg filme: Filme): Int

    @Query("SELECT * FROM Filme")
    fun allFavorites(): Flow<List<Filme>>

    @Query("SELECT COUNT(id) FROM Filme where id = :id")
    suspend fun isFavorite(id: String): Int
}