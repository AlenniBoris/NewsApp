package com.example.newsapp.presentation.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AppTopBar(
    hasButton: Boolean,
    text: String,
    textVisible: Boolean,
    navController: NavHostController
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 17.dp)
            .padding(horizontal = if (text == "Bookmarks") 0.dp else 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        if (hasButton){
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Btn back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        if (textVisible){
            Text(
                text = text,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = if (hasButton) TextAlign.End else TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
        }
    }
}