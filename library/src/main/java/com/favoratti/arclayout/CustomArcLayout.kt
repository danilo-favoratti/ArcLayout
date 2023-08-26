package com.favoratti.arclayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CustomArcLayout(
    modifier: Modifier = Modifier,
    alignment: Alignment? = null,
    anchorAngle: Float = 0f,
    sweepAngle: Float = 0f,
    radius: Dp,
    reverse: Boolean = false,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { items, constraints ->
        val childPlacements = mutableListOf<ChildPlacement>()
        val radiusPx = radius.toPx()

        val childConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val isReverse = alignment?.reverse ?: reverse

        if (isReverse) {
            items.reversed()
        } else {
            items
        }.forEachIndexed { index, measurable ->
            val localAnchorAngle = alignment?.anchorAngle ?: anchorAngle
            val localSweepAngle = alignment?.sweepAngle ?: sweepAngle

            val placeable = measurable.measure(childConstraints)
            val centerAdjustment = if (alignment == Alignment.CENTER) { 0f } else { .5f }
            val angle = localAnchorAngle + (index + centerAdjustment) * (localSweepAngle / items.size)
            val radians = Math.toRadians(angle.toDouble())

            val x = (radiusPx * cos(radians) - placeable.width / 2).toFloat()
            val y = (radiusPx * sin(radians) - placeable.height / 2).toFloat()
            val offset = Offset(x, y)

            childPlacements.add(
                ChildPlacement(
                    placeable = placeable,
                    position = offset
                )
            )
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            childPlacements.forEach { placement ->
                val positionPair = calculatePosition(alignment, placement, constraints)
                placement.placeable.place(
                    x = positionPair.first,
                    y = positionPair.second
                )
            }
        }
    }
}

private fun calculatePosition(
    alignment: Alignment?,
    placement: ChildPlacement,
    constraints: Constraints
) = when (alignment) {
    Alignment.LEFT -> {
        val x = (placement.position.x).toInt()
        val y = (constraints.maxHeight / 2 + placement.position.y).toInt()

        Pair(x, y)
    }

    Alignment.RIGHT -> {
        val x = (constraints.maxWidth + placement.position.x).toInt()
        val y = (constraints.maxHeight / 2 + placement.position.y).toInt()

        Pair(x, y)
    }

    Alignment.TOP -> {
        val x = (constraints.maxWidth / 2 + placement.position.x).toInt()
        val y = (placement.position.y).toInt()

        Pair(x, y)
    }

    Alignment.BOTTOM -> {
        val x = (constraints.maxWidth / 2 + placement.position.x).toInt()
        val y = (constraints.maxHeight + placement.position.y).toInt()

        Pair(x, y)
    }

    Alignment.CENTER -> {
        val x = (constraints.maxWidth / 2 + placement.position.x).toInt()
        val y = (constraints.maxHeight / 2 + placement.position.y).toInt()

        Pair(x, y)
    }

    Alignment.TOP_LEFT -> {
        val x = (placement.position.x).toInt()
        val y = (placement.position.y).toInt()

        Pair(x, y)
    }

    Alignment.TOP_RIGHT -> {
        val x = (constraints.maxWidth + placement.position.x).toInt()
        val y = placement.position.y.toInt()

        Pair(x, y)
    }

    Alignment.BOTTOM_LEFT -> {
        val x = (placement.position.x).toInt()
        val y = (constraints.maxHeight + placement.position.y).toInt()

        Pair(x, y)
    }

    Alignment.BOTTOM_RIGHT -> {
        val x = (constraints.maxWidth + placement.position.x).toInt()
        val y = (constraints.maxHeight + placement.position.y).toInt()

        Pair(x, y)
    }

    else -> {
        val x = (constraints.maxWidth / 2 + placement.position.x).toInt()
        val y = (constraints.maxHeight / 2 + placement.position.y).toInt()

        Pair(x, y)
    }
}

private data class ChildPlacement(
    val placeable: Placeable,
    val position: Offset
)