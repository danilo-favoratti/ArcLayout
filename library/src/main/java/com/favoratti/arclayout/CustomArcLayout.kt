package com.favoratti.arclayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import com.favoratti.arclayout.model.Parameters
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CustomArcLayout(
    modifier: Modifier = Modifier,
    parameters: Parameters,
    content: @Composable () -> Unit
) {
    fun calculateChildPositions(
        radiusPx: Float,
        childConstraints: Constraints,
        items: List<Measurable>
    ) = mutableListOf<ChildPlacement>().apply {

        items.organize(parameters.reverse).forEachIndexed { index, measurable ->
            val localAnchorAngle = parameters.anchorAngle
            val localSweepAngle = parameters.sweepAngle

            val placeable = measurable.measure(childConstraints)
            val angle = localAnchorAngle + (index + parameters.adjustment) * (localSweepAngle / items.size)
            val radians = Math.toRadians(angle.toDouble())

            val x = (radiusPx * cos(radians) - placeable.width / 2).toFloat()
            val y = (radiusPx * sin(radians) - placeable.height / 2).toFloat()
            val offset = Offset(x, y)

            add(
                ChildPlacement(
                    placeable = placeable,
                    initialPosition = Offset(0f, 0f),
                    position = offset
                )
            )
        }
    }

    Layout(
        modifier = modifier,
        content = content
    ) { items, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            calculateChildPositions(
                radiusPx = parameters.radius.toPx(),
                childConstraints = constraints.copy(minWidth = 0, minHeight = 0),
                items = items,
            ).forEach { placement ->
                val childPosition = parameters.alignment.calculateChildPosition(
                    x = placement.position.x,
                    y = placement.position.y,
                    maxWidth = constraints.maxWidth,
                    maxHeight = constraints.maxHeight
                )
                placement.placeable.place(
                    x = childPosition.x,
                    y = childPosition.y
                )
            }
        }
    }
}

private fun List<Measurable>.organize(reverse: Boolean = false) =
    if (reverse) {
        this.reversed()
    } else {
        this
    }

private data class ChildPlacement(
    val placeable: Placeable,
    val initialPosition: Offset,
    val position: Offset,
)