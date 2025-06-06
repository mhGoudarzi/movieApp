package com.mgoudarzi.movieapp.data.repository

import com.mgoudarzi.movieapp.data.remote.AuthApi
import com.mgoudarzi.movieapp.data.remote.Credential
import com.mgoudarzi.movieapp.data.remote.SessionDto
import com.mgoudarzi.movieapp.data.remote.TokenDto
import com.mgoudarzi.movieapp.data.util.API_KEY
import com.mgoudarzi.movieapp.domain.repository.AuthRepository
import com.mgoudarzi.movieapp.domain.util.Resource
import okio.IOException
import retrofit2.HttpException

class AuthRepositoryImpl(
  val authApi: AuthApi,
) : AuthRepository {

  val headers = mapOf(
    "accept" to "application/json",
    "content-type" to "application/json",
    "Authorization" to "Bearer $API_KEY"
  )

  override suspend fun login(username: String, password: String, token: String): Resource<SessionDto> {
    return try {
      val credential = Credential(
        username = username,
        password = password,
        token = token
      )

      val validation = authApi.validateToken(header = headers, credential = credential)

      if (validation.success) {
        val session = authApi.createSessionId(header = headers, TokenDto(token = credential.token))
        Resource.Success(data = session)
      } else {
        Resource.Error(message = "Failed to login")
      }

    } catch (e: HttpException) {

      Resource.Error(message = e.message)
    } catch (e: IOException) {
      Resource.Error(message = e.message)
    }
  }

  override suspend fun createToken(): Resource<String> {
    return try {
      Resource.Success(data = authApi.generateToken(header = headers).token)
    } catch (e: HttpException) {

      Resource.Error(message = e.message)
    } catch (e: IOException) {
      Resource.Error(message = e.message)
    }
  }
}