package com.favoratti.arclayout

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.arclayout.model.Alignment
import com.favoratti.arclayout.model.Parameters
import com.favoratti.arclayout.ui.theme.ComposableArcLayoutTheme
import com.favoratti.arclayout.util.CircularList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableArcLayoutTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArcLayoutDemoList()
                }
            }
        }
    }
}

@Composable
private fun ArcLayoutDemoList() {
    val listOfDemos: List<Pair<Alignment, Int>> = listOf(
        Pair(Alignment.LEFT, 7),
        Pair(Alignment.RIGHT, 7),
        Pair(Alignment.TOP, 7),
        Pair(Alignment.BOTTOM, 7),
        Pair(Alignment.CENTER, 12),
        Pair(Alignment.TOP_LEFT, 4),
        Pair(Alignment.TOP_RIGHT, 4),
        Pair(Alignment.BOTTOM_LEFT, 4),
        Pair(Alignment.BOTTOM_RIGHT, 4)
    )

    LazyColumn {
        itemsIndexed(listOfDemos) { _, item ->
            Row {
                Text(
                    modifier = Modifier.padding(start = 32.dp, top = 32.dp, bottom = 16.dp),
                    text = item.first.name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
            Row {
                ArcLayoutCard(
                    alignment = item.first,
                    quantity = item.second
                )
            }
        }
    }

    Row {
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
private fun ArcLayoutDemo(
    alignment: Alignment,
    quantity: Int = 4
) {
    ArcLayoutCard(
        alignment = alignment,
        quantity = quantity
    )
}

@Composable
private fun ArcLayoutCard(
    alignment: Alignment,
    quantity: Int
) {
    Card(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp)
            .fillMaxWidth()
            .height(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        ArcLayout(
            alignment = alignment,
            radius = 100.dp
        ) {
            PreviewItems(quantity)
        }
    }
}

private class ItemColor(
    val fontColor: Color,
    val backgroundColor: Color
)

private fun getColors(colorScheme: ColorScheme) = CircularList(
    listOf(
        ItemColor(
            fontColor = colorScheme.onPrimaryContainer,
            backgroundColor = colorScheme.primaryContainer
        ),
        ItemColor(
            fontColor = colorScheme.onSecondaryContainer,
            backgroundColor = colorScheme.secondaryContainer
        ),
        ItemColor(
            fontColor = colorScheme.onTertiaryContainer,
            backgroundColor = colorScheme.tertiaryContainer
        )
    )
)

@Composable
private fun PreviewItems(
    quantity: Int = 4
) {
    val colors = getColors(MaterialTheme.colorScheme)
    for (i in 1..quantity) {
        val itemColor = colors.next()
        val letter = ('A' + i - 1)
        Item(
            text = letter.toString(),
            fontColor = itemColor.fontColor,
            backgroundColor = itemColor.backgroundColor
        )
    }
}

@Composable
private fun Item(
    fontColor: Color = Color.Black,
    text: String,
    backgroundColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .wrapContentSize()
    ) {
        Text(
            style = MaterialTheme.typography.labelMedium.copy(color = fontColor),
            text = text
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoLeftPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(
            alignment = Alignment.LEFT,
            quantity = 8
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoRightPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(
            alignment = Alignment.RIGHT,
            quantity = 8
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoTopPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(
            alignment = Alignment.TOP,
            quantity = 8
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoBottomPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(
            alignment = Alignment.BOTTOM,
            quantity = 8
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoCenterPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(
            alignment = Alignment.CENTER,
            quantity = 12
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoTopLeftPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(alignment = Alignment.TOP_LEFT)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoTopRightPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(alignment = Alignment.TOP_RIGHT)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoBottomLeftPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(alignment = Alignment.BOTTOM_LEFT)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoBottomRightPreview() {
    ComposableArcLayoutTheme {
        ArcLayoutDemo(alignment = Alignment.BOTTOM_RIGHT)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun ArcLayoutDemoCustomPreview() {
    ComposableArcLayoutTheme {
        CustomArcLayout(
            modifier = Modifier.background(color = Color.Black),
            parameters = Parameters.builder(radius = 100.dp)
                .anchorAngle(235f)
                .sweepAngle(130f)
                .reverse(false)
                .build()
        ) {
            PreviewItems(quantity = 10)
        }
    }
}

