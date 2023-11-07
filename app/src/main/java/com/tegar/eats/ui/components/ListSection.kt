package com.tegar.eats.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegar.eats.ui.theme.ManropeFamiliy

@Composable
fun ListSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)


        ) {
            SectionText(title)
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = "See More",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 12.sp,
                    color = Color(0xff8C8C8C)
                ),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        content()
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.displayMedium.copy(
            fontSize = 20.sp,
            fontFamily = ManropeFamiliy,


        ),
        modifier = modifier

    )
}