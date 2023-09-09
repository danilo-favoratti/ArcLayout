package com.favoratti.arclayout.model

import androidx.compose.ui.unit.Dp

@Suppress("DataClassPrivateConstructor")
data class Parameters private constructor(
    val alignment: Alignment,
    val anchorAngle: Float,
    val sweepAngle: Float,
    val adjustment: Float,
    val reverse: Boolean,
    val radius: Dp
) {
    class Builder(private val radius: Dp) {
        private var alignment: Alignment = Alignment.CUSTOM
        private var anchorAngle: Float = Alignment.BOTTOM.anchorAngle
        private var sweepAngle: Float = Alignment.BOTTOM.sweepAngle
        private var adjustment: Float = Alignment.BOTTOM.adjustment
        private var reverse: Boolean = false

        fun alignment(alignment: Alignment): Builder {
            this.alignment = alignment
            return this
        }

        fun anchorAngle(anchorAngle: Float): Builder {
            this.anchorAngle = anchorAngle
            return this
        }

        fun sweepAngle(sweepAngle: Float): Builder {
            this.sweepAngle = sweepAngle
            return this
        }

        fun adjustment(adjustment: Float): Builder {
            this.adjustment = adjustment
            return this
        }

        fun reverse(reverse: Boolean): Builder {
            this.reverse = reverse
            return this
        }

        fun build(): Parameters {
            return Parameters(
                alignment = alignment,
                anchorAngle = anchorAngle,
                sweepAngle = sweepAngle,
                radius = radius,
                adjustment = adjustment,
                reverse = reverse
            )
        }
    }

    companion object {
        fun builder(radius: Dp, alignment: Alignment = Alignment.CUSTOM): Builder {
            return Builder(radius).apply {
                if (alignment != Alignment.CUSTOM) {
                    alignment(alignment)
                    anchorAngle(alignment.anchorAngle)
                    sweepAngle(alignment.sweepAngle)
                    adjustment(alignment.adjustment)
                    reverse(alignment.reverse)
                }
            }
        }
    }
}

data class ChildPosition(
    val x: Int,
    val y: Int
)

enum class Alignment(
    val anchorAngle: Float = 0f,
    val sweepAngle: Float = 0f,
    val adjustment: Float = 0.5f,
    val reverse: Boolean = false
) {
    LEFT(anchorAngle = 270f, sweepAngle = 180f, reverse = false),
    RIGHT(anchorAngle = 90f, sweepAngle = 180f, reverse = true),
    TOP(anchorAngle = 0f, sweepAngle = 180f, reverse = true),
    BOTTOM(anchorAngle = 180f, sweepAngle = 180f, reverse = false),

    CENTER(anchorAngle = 270f, sweepAngle = 360f, reverse = false, adjustment = 0f),

    TOP_LEFT(anchorAngle = 0f, sweepAngle = 90f, reverse = true),
    TOP_RIGHT(anchorAngle = 90f, sweepAngle = 90f, reverse = true),
    BOTTOM_LEFT(anchorAngle = 270f, sweepAngle = 90f, reverse = false),
    BOTTOM_RIGHT(anchorAngle = 180f, sweepAngle = 90f, reverse = false),

    CUSTOM;

    fun calculateChildPosition(
        x: Float,
        y: Float,
        maxWidth: Int,
        maxHeight: Int
    ) = when (this) {
        LEFT -> {
            ChildPosition(
                x = x.toInt(),
                y = (maxHeight / 2 + y).toInt()
            )
        }

        RIGHT -> {
            ChildPosition(
                x = (maxWidth + x).toInt(),
                y = (maxHeight / 2 + y).toInt()
            )
        }

        TOP -> {
            ChildPosition(
                x = (maxWidth / 2 + x).toInt(),
                y = y.toInt()
            )
        }

        BOTTOM -> {
            ChildPosition(
                x = (maxWidth / 2 + x).toInt(),
                y = (maxHeight + y).toInt()
            )
        }

        CENTER -> {
            ChildPosition(
                x = (maxWidth / 2 + x).toInt(),
                y = (maxHeight / 2 + y).toInt()
            )
        }

        TOP_LEFT -> {
            ChildPosition(
                x = x.toInt(),
                y = y.toInt()
            )
        }

        TOP_RIGHT -> {
            ChildPosition(
                x = (maxWidth + x).toInt(),
                y = y.toInt()
            )
        }

        BOTTOM_LEFT -> {
            ChildPosition(
                x = x.toInt(),
                y = (maxHeight + y).toInt()
            )
        }

        BOTTOM_RIGHT -> {
            ChildPosition(
                x = (maxWidth + x).toInt(),
                y = (maxHeight + y).toInt()
            )
        }

        CUSTOM -> {
            ChildPosition(
                x = (maxWidth / 2 + x).toInt(),
                y = (maxHeight / 2 + y).toInt()
            )
        }
    }
}