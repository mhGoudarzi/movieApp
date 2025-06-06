package com.mgoudarzi.movieapp.domain.util

import com.mgoudarzi.movieapp.data.remote.MovieDto
import com.mgoudarzi.movieapp.domain.movie.MovieItem

fun MovieDto.toModel(): MovieItem = MovieItem(
  id = this.id,
  title = this.title,
  backdrop_path = this.backdrop_path,
  genre_ids = this.genre_ids,
  original_language = this.original_language,
  poster_path = this.poster_path,
  release_date = this.release_date
)