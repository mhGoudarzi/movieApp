package com.mgoudarzi.movieapp.ui.search

sealed interface SearchEvent {
  data class ChangeQuery(val query: String) : SearchEvent
  data class Search(val query: String) : SearchEvent
  data class RemoveHistory(val query: String) : SearchEvent
}