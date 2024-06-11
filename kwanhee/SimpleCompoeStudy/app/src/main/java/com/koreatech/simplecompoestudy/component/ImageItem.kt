package com.koreatech.simplecompoestudy.component

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.koreatech.simplecompoestudy.R
import com.koreatech.simplecompoestudy.data.response.SearchImageDocumentsResponse
import com.koreatech.simplecompoestudy.utils.HorizontalSpacer
import com.koreatech.simplecompoestudy.utils.VerticalSpacer

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    context: Context,
    imageDocument: SearchImageDocumentsResponse?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(IntrinsicSize.Min)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .crossfade(true)
                .data(imageDocument?.imageUrl)
                .build(),
            contentDescription = null,
            placeholder = ColorPainter(Color.LightGray),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .size(80.dp)
        )
        HorizontalSpacer(width = 8.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = imageDocument?.displaySitename?.ifEmpty { "제목 없음" } ?: "제목 없음",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Text(
                    text = imageDocument?.collection ?: "",
                    color = Color.Black,
                    fontSize = 12.sp
                )
            }
            VerticalSpacer(height = 2.dp)
            Text(
                text = imageDocument?.docUrl?.ifEmpty { "주소 없음" } ?: "주소 없음",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                fontSize = 12.sp,
                style = TextStyle(
                    lineHeight = 1.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
            VerticalSpacer(height = 2.dp)
            Text(
                text = imageDocument?.datetime ?: "",
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageItemPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ImageItem(
            context = LocalContext.current,
            imageDocument = SearchImageDocumentsResponse(
                collection = "news",
                thumbnailUrl = "https://search2.kakaocdn.net/argon/130x130_85_c/36hQpoTrVZp",
                imageUrl = "http://t1.daumcdn.net/news/201706/21/kedtv/20170621155930292vyyx.jpg",
                width = 540,
                height = 457,
                displaySitename = "한국경제TV",
                docUrl = "http://v.media.daum.net/v/20170621155930002",
                datetime = "2017-06-21T15:59:30.000+09:00"
            )
        )
    }
}