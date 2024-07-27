package com.tegar.eats.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegar.eats.R
import com.google.accompanist.pager.*
import com.tegar.eats.ui.theme.Orange
import com.tegar.eats.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun OnboardingScreen(
    navigateToSurvey: (Long) -> Unit,
) {
    val pagerState  = rememberPagerState()
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                       Row(

                           verticalAlignment = Alignment.CenterVertically ) {
                           Image(
                               painter = painterResource(id = R.drawable.logo), // Ganti dengan logo Anda
                               contentDescription = "Logo",
                               modifier = Modifier


                           )
                            Text(
                                 text = "Eats",
                                 style =  Typography.labelMedium.copy(
                                    fontSize = 20.sp,
                                 ),

                                 fontWeight = FontWeight.Bold,
                                 color = Orange,
                                 modifier = Modifier.padding(start = 8.dp)
                            )
                       }
                        TextButton(
                            onClick = { navigateToSurvey(0)},

                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            Text(
                                text = "Skip",
                                style = Typography.bodyMedium.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Gray
                            )
                        }
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HorizontalPager(state = pagerState, count = 3 , modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)) { page ->
                    OnboardingPage(page)



                }

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                ) {
                    HorizontalPagerIndicator(
                        pagerState = pagerState,

                    )
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                if (pagerState.currentPage + 1 < pagerState.pageCount) {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                } else {
                                    // TODO: Navigate to next screen
                                    navigateToSurvey(0)
                                }
                            }
                        },
                        shape = if (pagerState.currentPage + 1 < pagerState.pageCount) CircleShape else RoundedCornerShape(16.dp),
                        containerColor = Orange,
                        contentColor = Color.White,
                        elevation =   FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                        modifier = if(pagerState.currentPage + 1 < pagerState.pageCount) Modifier.size(56.dp) else Modifier
                            .size(100.dp)
                            .height(56.dp)
                            .fillMaxWidth(0.5f)

                    ) {
                        // if end of the page, change the icon to continue
                        if (pagerState.currentPage + 1 < pagerState.pageCount) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Next"
                            )
                        } else {
                            Text(
                                text = "Continue",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White
                            )
                        }

                    }
                }
                }

        }
    )
}

@Composable
fun OnboardingPage(page: Int) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = when (page) {
                0 -> R.drawable.il_1
                1 -> R.drawable.il_2
                else -> R.drawable.il_3
            }), // Ganti dengan gambar Anda
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 32.dp)
        )
        Text(
            text = when (page) {
                0 -> "Welcome to Eats!"
                1 -> "Exclusive Offer for New Users"
                else -> "Fast and Reliable Delivery"
            },
            style = Typography.labelMedium.copy(
                fontSize = 20.sp,
                fontWeight =  FontWeight.Bold
            ),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = when (page) {
                0 -> "Where Flavor Meets Your Doorstep"
                1 -> "Enjoy special discounts created just for you. Save big on your favorite meals!"
                else -> "Your food, delivered quickly and reliably. Hot and fresh, right to your door!"
            },
            style =  Typography.bodyMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.Red,
    inactiveColor: Color = Color.Gray,
    indicatorWidth: Dp = 14.dp,
    indicatorSpacing: Dp = 8.dp
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(indicatorSpacing),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pagerState.pageCount) { page ->
            val color = if (pagerState.currentPage == page) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .size(indicatorWidth, 8.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}
