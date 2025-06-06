package com.mgoudarzi.movieapp.domain.repository

import com.mgoudarzi.movieapp.domain.movie.Movie
import com.mgoudarzi.movieapp.domain.movie.MovieItem
import com.mgoudarzi.movieapp.domain.util.ListSort
import com.mgoudarzi.movieapp.domain.util.ListTitle
import com.mgoudarzi.movieapp.domain.util.Resource

interface MovieRepository {
  suspend fun getMovieList(listTitle: ListTitle? = null, page: Int = 1, sort: ListSort = ListSort.POPULARITY_DESC): Resource<List<MovieItem>>
  suspend fun searchMovie(query: String = "", page: Int = 1): Resource<List<MovieItem>>
  suspend fun getMovie(movieId: Int): Resource<Movie>
  suspend fun getMovieStatus(movieId: Int, sessionId: String): Resource<Boolean>
}