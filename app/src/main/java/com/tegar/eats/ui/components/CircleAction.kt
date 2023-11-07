package com.tegar.eats.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tegar.eats.R
import com.tegar.eats.ui.theme.DarkBlue
import com.tegar.eats.ui.theme.EatsTheme

@Composable
fun CircleAction(@DrawableRes icon: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier

            .border(
                1.dp, Color(0xffE9EBEE),
                CircleShape
            )


            .clip(CircleShape)
            .size(50.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center

    ) {

        Icon(
            painter = painterResource(icon),
            tint = Color(0xff999999),
            contentDescription = stringResource(id = R.string.top_up_icon),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircleActionPreviw() {
    EatsTheme {
        CircleAction(
            icon = R.drawable.ic_filter,
            onClick = {}
        )
    }
}
