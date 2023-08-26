package com.favoratti.arclayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ArcLayout(
    modifier: Modifier = Modifier,
    alignment: Alignment,
    radius: Dp,
    content: @Composable () -> Unit
) {
    CustomArcLayout(
        modifier = modifier,
        alignment = alignment,
        anchorAngle = alignment.anchorAngle,
        sweepAngle = alignment.sweepAngle,
        radius = radius,
        content = content
    )
}

enum class Alignment(
    val anchorAngle: Float,
    val sweepAngle: Float,
    val reverse: Boolean
) {
    LEFT(anchorAngle = 270f, sweepAngle = 180f, reverse = false),
    RIGHT(anchorAngle = 90f, sweepAngle = 180f, reverse = true),
    TOP(anchorAngle = 0f, sweepAngle = 180f, reverse = true),
    BOTTOM(anchorAngle = 180f, sweepAngle = 180f, reverse = false),

    CENTER(anchorAngle = 270f, sweepAngle = 360f, reverse = false),

    TOP_LEFT(anchorAngle = 0f, sweepAngle = 90f, reverse = true),
    TOP_RIGHT(anchorAngle = 90f, sweepAngle = 90f, reverse = true),
    BOTTOM_LEFT(anchorAngle = 270f, sweepAngle = 90f, reverse = false),
    BOTTOM_RIGHT(anchorAngle = 180f, sweepAngle = 90f, reverse = false)
}