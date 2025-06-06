package com.mgoudarzi.movieapp.domain.repository

import com.mgoudarzi.movieapp.data.remote.SessionDto
import com.mgoudarzi.movieapp.domain.util.Resource

interface AuthRepository {
  suspend fun createToken(): Resource<String>
  suspend fun login(username: String, password: String, token: String): Resource<SessionDto>
}