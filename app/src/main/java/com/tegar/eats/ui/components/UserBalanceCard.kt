package com.tegar.eats.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tegar.eats.R
import com.tegar.eats.ui.theme.DarkBlue
import com.tegar.eats.ui.theme.EatsTheme
import com.tegar.eats.utils.LocalCustomColorsPalette

@Composable
fun UserBalanceCard(
    modifier: Modifier = Modifier,
    balance: String,
    onTopUpClick: () -> Unit,
    onPayClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(16.dp)
    ) {
        CardBackground()
        UserCardDetails(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            balance = balance,
            onTopUpClick = onTopUpClick,
            onPayClick = onPayClick
        )
    }
}

@Composable
fun CardBackground() {
    Image(
        painter = painterResource(id = R.drawable.bg_user_card),
        contentDescription = stringResource(id = R.string.user_card_image),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UserCardDetails(
    modifier: Modifier = Modifier,
    balance: String,
    onTopUpClick: () -> Unit,
    onPayClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(LocalCustomColorsPalette.current.costumeCardColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
           Row{
               UserBalanceSection(balance = balance)
               Spacer(modifier = Modifier.weight(1.0f))
               UserActionsSection(
                   onTopUpClick = onTopUpClick,
                   onPayClick = onPayClick
               )
           }
        }
    }
}

@Composable
fun UserBalanceSection(balance: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_balance),
                    contentDescription = stringResource(id = R.string.balance_icon),
                    modifier = Modifier.size(19.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.your_balance),
                    style = MaterialTheme.typography.headlineSmall,
                    color = LocalCustomColorsPalette.current.costumeSpecialColor
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = balance,
                style = MaterialTheme.typography.displayMedium,
                color = LocalCustomColorsPalette.current.costumeSpecialColor

            )
        }
    }
}

@Composable
fun UserActionsSection(
    onTopUpClick: () -> Unit,
    onPayClick: () -> Unit
) {
    Row {
        VerticalButton(
            title = stringResource(id = R.string.top_up),
            icon = R.drawable.ic_top_up,
            onClick = onTopUpClick
        )
        Spacer(Modifier.width(20.dp))
        VerticalButton(
            title = stringResource(id = R.string.pay),
            icon = R.drawable.ic_scan,
            onClick = onPayClick
        )
    }
}

@Composable
fun VerticalButton(
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = LocalCustomColorsPalette.current.costumeSpecialColor,

                            shape = RoundedCornerShape(20)
                )
                .clickable(onClick = onClick)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(id = R.string.top_up_icon),
                tint = LocalCustomColorsPalette.current.costumeBlackWhite
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = LocalCustomColorsPalette.current.costumeSpecialColor

        )
    }
}

@Composable
@Preview(showBackground = true)
fun UserCardPreview() {
    EatsTheme {
        UserBalanceCard(
            balance = "Rp 1.230.232",
            onTopUpClick = { /* Handle top-up click */ },
            onPayClick = { /* Handle pay click */ }
        )
    }
}
