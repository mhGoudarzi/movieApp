package com.mgoudarzi.movieapp.ui.util.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mgoudarzi.movieapp.ui.util.MEDIUM_SPACE
import com.mgoudarzi.movieapp.ui.util.SMALL_SPACE

@Composable
fun MovieListGrid(
  modifier: Modifier = Modifier,
  lazyGridState: LazyGridState,
  content: LazyGridScope.() -> Unit
) {
  LazyVerticalGrid(
    modifier = modifier,
    contentPadding = PaddingValues(MEDIUM_SPACE.dp),
    verticalArrangement = Arrangement.spacedBy(SMALL_SPACE.dp),
    horizontalArrangement = Arrangement.spacedBy(SMALL_SPACE.dp),
    columns = GridCells.Adaptive(minSize = 100.dp),
    state = lazyGridState,
    content = content
  )
}