package com.mgoudarzi.movieapp.ui.util

import com.mgoudarzi.movieapp.domain.util.ListTitle
import kotlinx.serialization.Serializable


@Serializable
data object MainScreen

@Serializable
data class MovieScreen(val movieId: Int)

@Serializable
data class MovieList(val listTitle: ListTitle)

@Serializable
data object HomeScreen

@Serializable
data object ProfileScreen

@Serializable
data object FavoriteScreen

@Serializable
data object SearchScreen