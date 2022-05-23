package com.compose.jreader.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.compose.jreader.R
import com.compose.jreader.data.model.Book
import com.compose.jreader.ui.theme.Cyan200
import com.compose.jreader.utils.*


@Composable
fun ListCard(
    book: Book,
    onClickDetails: (String) -> Unit
) {

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    Card(
        shape = RoundedCornerShape(listCardCorner),
        backgroundColor = Color.White,
        elevation = listCardElevation,
        modifier = Modifier
            .padding(listCardPadding)
            .height(listCardHeight)
            .width(listCardWidth)
    ) {

        Box(
            modifier = Modifier
                .clickable {
                    book.title?.let { onClickDetails.invoke(it) }
                },
            contentAlignment = Alignment.BottomStart
        ) {

            Column(
                modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
                horizontalAlignment = Alignment.Start
            ) {

                Row(horizontalArrangement = Arrangement.Center) {

                    AsyncImage(
                        model = book.photoUrl,
                        contentDescription = stringResource(R.string.desc_book_image),
                        modifier = Modifier
                            .height(bookImageHeight)
                            .width(bookImageWidth)
                            .padding(bookImagePadding)
                    )
                    Spacer(modifier = Modifier.width(bookImageSpacerWidth))
                    Column(
                        modifier = Modifier.padding(top = bookColumnTopPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.FavoriteBorder,
                            contentDescription = stringResource(R.string.desc_fav_icon),
                            modifier = Modifier.padding(bottom = bookFavoriteIconBottomPadding)
                        )
                        BookRating(score = 3.5)

                    }

                }

                Text(
                    text = book.title ?: "",
                    modifier = Modifier.padding(listCardTextPadding),
                    fontWeight = FontWeight.Bold,
                    maxLines = 2, overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book.authors ?: "",
                    modifier = Modifier.padding(listCardTextPadding),
                    style = MaterialTheme.typography.caption
                )

                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    RoundedButton(label = "Reading", radius = 70) {

                    }
                }

            }

        }

    }

}

@Composable
fun BookRating(score: Double) {
    Surface(
        modifier = Modifier
            .height(ratingSurfaceHeight)
            .padding(ratingSurfacePadding),
        shape = RoundedCornerShape(50),
        elevation = ratingSurfaceElevation
    ) {

        Column(modifier = Modifier.padding(ratingColumnPadding)) {
            Icon(
                imageVector = Icons.Filled.StarBorder,
                contentDescription = stringResource(R.string.desc_star_icon),
                modifier = Modifier.padding(ratingIconPadding)
            )
            Text(
                modifier = Modifier.fillMaxWidth(0.8F),
                text = score.toString(),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun RoundedButton(
    label: String,
    radius: Int,
    onPress: () -> Unit
) {

    Button(
        onClick = {
            onPress.invoke()
        },
        shape = RoundedCornerShape(
            bottomEndPercent = radius,
            topStartPercent = radius
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Cyan200
        ),
        elevation = ButtonDefaults.elevation(roundedButtonElevation),
        modifier = Modifier
            .width(roundedButtonWidth)
            .height(roundedButtonHeightIn),
        contentPadding = PaddingValues(roundedButtonContentPadding)
    ) {

        Text(
            text = label,
            style = TextStyle(
                color = Color.White,
                fontSize = roundedButtonTextSize
            )
        )

    }

}