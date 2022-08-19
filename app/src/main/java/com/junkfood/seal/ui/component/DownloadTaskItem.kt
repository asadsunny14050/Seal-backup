package com.junkfood.seal.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junkfood.seal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadTaskItem(
    modifier: Modifier = Modifier.padding(12.dp),
    imageModel: Any = R.drawable.ic_launcher_foreground,
    title: String = "sample title ".repeat(5),
    author: String = "author sample ".repeat(5),
    state: String = "Enqueued",
    isExpanded: Boolean = false,
    progress: Float = -1f
) {
    var isExpanded by remember { mutableStateOf(isExpanded) }
    ElevatedCard(modifier = modifier, onClick = { isExpanded = !isExpanded }) {
        Column() {
            Box() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    /*AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(0.45f)
                            .aspectRatio(16f / 9f, matchHeightConstraintsFirst = true),
                        model = imageModel,
                        contentDescription = stringResource(R.string.thumbnail),
                        contentScale = ContentScale.Crop,
                    )*/
                    Image(
                        painter = painterResource(id = R.drawable.sample),
                        modifier = Modifier
                            .padding(12.dp)
                            .weight(1f)
                            .clip(MaterialTheme.shapes.small)
                            .aspectRatio(16f / 10f, matchHeightConstraintsFirst = true),
                        contentDescription = stringResource(R.string.thumbnail),
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(end = 12.dp)
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 2, overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier.padding(top = 3.dp),
                            text = author,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = state,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                val animatedDegree = animateFloatAsState(targetValue = if (isExpanded) 0f else 180f)
                FilledTonalIconButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .size(24.dp),
                    onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        Icons.Outlined.ExpandLess,
                        null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.rotate(animatedDegree.value)
                    )
                }
            }
            AnimatedVisibility(visible = isExpanded) {
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .height(1.dp)
                        .clip(MaterialTheme.shapes.small)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .padding(horizontal = 12.dp)
                        .padding(top = 4.dp, bottom = 4.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(4f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text =
                            "This is a sample of very long progress text. ".repeat(3),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    val containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(15.dp)
                    val contentColor = contentColorFor(backgroundColor = containerColor)
                    Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.End) {
                        FilledIconButton(
                            onClick = { },
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = containerColor, contentColor = contentColor
                            )
                        ) {
                            Icon(
                                Icons.Rounded.Stop,
                                null,
                            )
                        }
                        FilledTonalIconButton(
                            onClick = { }, colors = IconButtonDefaults.iconButtonColors(
                                containerColor = containerColor, contentColor = contentColor
                            )
                        ) {
                            Icon(
                                Icons.Outlined.MoreHoriz,
                                null,
                            )
                        }
                    }
                }
            }
            if (progress < 0)
                LinearProgressIndicator(
                    Modifier
                        .fillMaxWidth()
                        .padding()
                )
            else
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(), progress = progress
                )
        }
    }
}

@Composable
@Preview
fun CardPreview() {
    Column() {
        DownloadTaskItem()
        DownloadTaskItem(isExpanded = true, progress = 1f, state = "Completed")
    }

}