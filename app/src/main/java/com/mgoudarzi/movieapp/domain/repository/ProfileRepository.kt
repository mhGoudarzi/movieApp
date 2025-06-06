package com.mgoudarzi.movieapp.domain.repository

import com.mgoudarzi.movieapp.data.remote.Profile
import com.mgoudarzi.movieapp.domain.movie.MovieItem
import com.mgoudarzi.movieapp.domain.util.Resource

interface ProfileRepository {
  suspend fun getProfile(sessionId: String): Resource<Profile>
  suspend fun changeFavorite(favorite: Boolean, movieId: Int, sessionId: String): Resource<Boolean>
  suspend fun getFavoriteMovies(page: Int, sessionId: String): Resource<List<MovieItem>>
}