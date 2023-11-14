package com.tegar.eats.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
    Column(

        modifier =  modifier.fillMaxSize().padding(16.dp)
    ){
        Row(
            horizontalArrangement =  Arrangement.spacedBy(5.dp)
        ){
         Text("Profile screen ")
        }

    }
}