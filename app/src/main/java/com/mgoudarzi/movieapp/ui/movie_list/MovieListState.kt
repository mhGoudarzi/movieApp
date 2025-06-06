package com.mgoudarzi.movieapp.ui.movie_list

import com.mgoudarzi.movieapp.domain.movie.MovieItem

data class MovieListState(
  val movies: List<MovieItem> = emptyList(),
  val isLoading: Boolean = true,
  val error: String? = null,
)