package com.mgoudarzi.movieapp.ui.movie

import com.mgoudarzi.movieapp.domain.movie.Movie

data class MovieState(
  val movie: Movie? = null,
  val error: String? = null,
  val isLoading: Boolean = true,
  val isFavorite: Boolean = false,
)