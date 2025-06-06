package com.mgoudarzi.movieapp.domain.repository

import com.mgoudarzi.movieapp.domain.history.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
  fun getAll(): Flow<List<History>>
  suspend fun addHistory(history: History)
  suspend fun removeHistory(query: String)
}