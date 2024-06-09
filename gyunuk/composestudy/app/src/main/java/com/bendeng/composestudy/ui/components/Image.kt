package com.bendeng.composestudy.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bendeng.composestudy.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun RoundedGlideImage(
    imageModel: Any,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Painter = painterResource(R.drawable.ic_image),
    error: Painter = painterResource(R.drawable.ic_image),
    size : Dp = 120.dp
) {
    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        GlideImage(
            imageModel = imageModel,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier
                .size(size)
                .clip(shape),
            placeHolder = placeholder,
            error = error,
        )
    }
}