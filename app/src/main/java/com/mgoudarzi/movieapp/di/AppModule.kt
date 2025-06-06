package com.mgoudarzi.movieapp.di

import android.content.Context
import androidx.room.Room
import com.mgoudarzi.movieapp.data.local.AppDatabase
import com.mgoudarzi.movieapp.data.remote.AuthApi
import com.mgoudarzi.movieapp.data.remote.MovieApi
import com.mgoudarzi.movieapp.data.remote.ProfileApi
import com.mgoudarzi.movieapp.data.repository.AuthRepositoryImpl
import com.mgoudarzi.movieapp.data.repository.HistoryRepositoryImpl
import com.mgoudarzi.movieapp.data.repository.MovieRepositoryImpl
import com.mgoudarzi.movieapp.data.repository.ProfileRepositoryImpl
import com.mgoudarzi.movieapp.data.util.API_BASE_URL
import com.mgoudarzi.movieapp.data.util.DataStoreManager
import com.mgoudarzi.movieapp.domain.repository.AuthRepository
import com.mgoudarzi.movieapp.domain.repository.HistoryRepository
import com.mgoudarzi.movieapp.domain.repository.MovieRepository
import com.mgoudarzi.movieapp.domain.repository.ProfileRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideMovieRepository(
    movieApi: MovieApi,
  ): MovieRepository =
    MovieRepositoryImpl(
      movieApi = movieApi,
    )

  @Provides
  @Singleton
  fun provideAuthRepository(
    authApi: AuthApi
  ): AuthRepository = AuthRepositoryImpl(
    authApi = authApi
  )

  @Provides
  @Singleton
  fun provideProfileRepository(
    profileApi: ProfileApi,
  ): ProfileRepository =
    ProfileRepositoryImpl(
      profileApi = profileApi
    )

  @Provides
  @Singleton
  fun provideHistoryRepository(
    db: AppDatabase,
  ): HistoryRepository =
    HistoryRepositoryImpl(
      historyDao = db.historyDao
    )


  @Provides
  @Singleton
  fun provideDataStoreManager(@ApplicationContext context: Context) =
    DataStoreManager(context)

  @Provides
  @Singleton
  fun provideHistoryDatabase(
    @ApplicationContext context: Context
  ): AppDatabase =
    Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "app-db"
    ).build()

  @Provides
  @Singleton
  fun provideMovieApi(): MovieApi = Retrofit.Builder()
    .baseUrl(API_BASE_URL)
    .addConverterFactory(
      MoshiConverterFactory.create(
        Moshi.Builder()
          .addLast(KotlinJsonAdapterFactory())
          .build()
      )
    )
    .build()
    .create(MovieApi::class.java)

  @Provides
  @Singleton
  fun provideAuthApi(): AuthApi = Retrofit.Builder()
    .baseUrl(API_BASE_URL)
    .addConverterFactory(
      MoshiConverterFactory.create(
        Moshi.Builder()
          .addLast(KotlinJsonAdapterFactory())
          .build()
      )
    )
    .build()
    .create(AuthApi::class.java)

  @Provides
  @Singleton
  fun provideProfileApi(): ProfileApi = Retrofit.Builder()
    .baseUrl(API_BASE_URL)
    .addConverterFactory(
      MoshiConverterFactory.create(
        Moshi.Builder()
          .addLast(KotlinJsonAdapterFactory())
          .build()
      )
    )
    .build()
    .create(ProfileApi::class.java)
}