package br.com.radcloud.filmes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val id: String,
    val poster_path: String?,
    val original_title: String?,
    val vote_average: String?,
    val overview: String?,
    val release_date: String?
): Parcelable
