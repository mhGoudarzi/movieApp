package com.mgoudarzi.movieapp.data.repository

import com.mgoudarzi.movieapp.data.local.HistoryDao
import com.mgoudarzi.movieapp.domain.history.History
import com.mgoudarzi.movieapp.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(
  val historyDao: HistoryDao
) : HistoryRepository {
  override fun getAll(): Flow<List<History>> =
    historyDao.getAll()

  override suspend fun addHistory(history: History) {
    historyDao.insertHistory(history)
  }

  override suspend fun removeHistory(query: String) {
    historyDao.removeHistory(query)
  }
}