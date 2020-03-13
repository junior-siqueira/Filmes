package br.com.radcloud.filmes.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Filme (
    @PrimaryKey
    val id: String,
    val poster_path: String?,
    val original_title: String?,
    val vote_average: String?,
    val overview: String?,
    val release_date: String?
)