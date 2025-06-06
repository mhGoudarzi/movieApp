package com.mgoudarzi.movieapp.data.repository

import coil3.network.HttpException
import com.mgoudarzi.movieapp.data.remote.MovieApi
import com.mgoudarzi.movieapp.data.remote.MovieListDto
import com.mgoudarzi.movieapp.data.util.API_KEY
import com.mgoudarzi.movieapp.domain.movie.Movie
import com.mgoudarzi.movieapp.domain.movie.MovieItem
import com.mgoudarzi.movieapp.domain.repository.MovieRepository
import com.mgoudarzi.movieapp.domain.util.ListSort
import com.mgoudarzi.movieapp.domain.util.ListTitle
import com.mgoudarzi.movieapp.domain.util.Resource
import com.mgoudarzi.movieapp.domain.util.toModel
import java.io.IOException

class MovieRepositoryImpl(
  val movieApi: MovieApi,
) : MovieRepository {

  companion object {
    val defaultHeader = mapOf(
      "accept" to "application/json",
      "authorization" to "Bearer $API_KEY"
    )
  }

  override suspend fun getMovieList(listTitle: ListTitle?, page: Int, sort: ListSort): Resource<List<MovieItem>> {
    var data = MovieListDto(emptyList())
    return try {
      if (listTitle != null) {
        when (listTitle) {
          ListTitle.NowPlaying -> {
            data = movieApi.getNowPlaying(header = defaultHeader, page = page)
          }

          ListTitle.Popular -> {
            data = movieApi.getPopular(header = defaultHeader, page = page)
          }

          ListTitle.TopRated -> {
            data = movieApi.getTopRated(header = defaultHeader, page = page)
          }

          ListTitle.BestSelling -> {
            data = movieApi.getMovieList(header = defaultHeader, page = page, sort = ListSort.REVENUE_DESC.value)
          }
        }
      } else {
        data = movieApi.getMovieList(header = defaultHeader, page = page, sort = sort.value)
      }

      Resource.Success(data = data.movies.map { it.toModel() })
    } catch (e: HttpException) {
      Resource.Error(message = e.message ?: "Network Error")
    } catch (e: IOException) {
      Resource.Error(message = e.message ?: "Unknown Error")
    }
  }

  override suspend fun searchMovie(query: String, page: Int): Resource<List<MovieItem>> {
    return try {
      val data = movieApi.searchMovie(header = defaultHeader, query = query, page = page)
      if (data.movies.isEmpty()) throw IOException()
      Resource.Success(data = data.movies.map { it.toModel() })

    } catch (e: HttpException) {
      Resource.Error(message = e.message ?: "Network Error")
    } catch (e: IOException) {
      Resource.Error(message = e.message ?: "Unknown Error")
    }
  }

  override suspend fun getMovie(movieId: Int): Resource<Movie> {
    return try {
      val movie = movieApi.getMovie(header = defaultHeader, movieId = movieId)
      with(movie) {
        Resource.Success(
          data = Movie(
            id = id,
            backdrop_path = backdrop_path,
            genres = genres.map {
              it.name
            },
            origin_country = origin_country,
            original_language = original_language,
            original_title = original_title,
            overview = overview,
            poster_path = poster_path,
            release_date = release_date,
            status = status,
            tagline = tagline,
            title = title,
            vote_average = vote_average,
            vote_count = vote_count
          )
        )
      }
    } catch (e: HttpException) {
      Resource.Error(message = e.message ?: "Network Error")
    } catch (e: IOException) {
      Resource.Error(message = e.message ?: "Unknown Error")
    }
  }

  override suspend fun getMovieStatus(movieId: Int, sessionId: String): Resource<Boolean> {
    return try {
      val data = movieApi.movieStatus(header = defaultHeader, movieId = movieId, sessionId = sessionId)
      Resource.Success(data = data.favorite)
    } catch (e: HttpException) {
      Resource.Error(message = e.message ?: "Network Error")
    } catch (e: IOException) {
      Resource.Error(message = e.message ?: "Unknown Error")
    }
  }
}