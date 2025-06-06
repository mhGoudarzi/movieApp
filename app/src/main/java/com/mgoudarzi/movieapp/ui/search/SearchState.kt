package com.mgoudarzi.movieapp.ui.search

import com.mgoudarzi.movieapp.domain.movie.MovieItem

data class SearchState(
  val movies: List<MovieItem> = emptyList(),
  val isLoaded: Boolean = false,
  val error: String? = null,
  val endReached: Boolean = false,
)