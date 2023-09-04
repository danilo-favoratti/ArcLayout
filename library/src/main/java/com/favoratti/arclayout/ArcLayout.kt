package com.favoratti.arclayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.favoratti.arclayout.model.Alignment
import com.favoratti.arclayout.model.Parameters

@Composable
fun ArcLayout(
    modifier: Modifier = Modifier,
    alignment: Alignment,
    radius: Dp,
    content: @Composable () -> Unit
) {
    CustomArcLayout(
        modifier = modifier,
        parameters = Parameters.builder(
            alignment = alignment,
            radius = radius
        ).build(),
        content = content
    )
}

