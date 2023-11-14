package com.tegar.eats.ui.screen.profile

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.tegar.eats.R
import com.tegar.eats.onNodeWithStringId
import com.tegar.eats.ui.theme.EatsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    @Before
    fun setUp(){
        composeTestRule.setContent {
            EatsTheme {

                ProfileScreen()
            }
        }
    }



    @Test
    fun navHost_verifyAllDataExist() {
        composeTestRule.onNodeWithTag("avatar").assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.author_name).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.author_email).assertIsDisplayed()

    }
}