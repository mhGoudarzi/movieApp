package com.mgoudarzi.movieapp.ui.favorite

import com.mgoudarzi.movieapp.domain.movie.MovieItem

data class FavoriteState(
  val movies: List<MovieItem> = emptyList(),
  val isLoading: Boolean = true,
  val error: String? = null,
)
