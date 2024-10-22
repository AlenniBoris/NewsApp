package com.example.newsapp.presentation.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Red
import com.example.newsapp.ui.theme.White

@Composable
fun EmptyScreen(
    onExploreClicked: () -> Unit,
    text: String,
    btnText: String = "Explore",
    hasInternet: Boolean = true,
    isLoading: Boolean = false
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isLoading){
            CircularProgressIndicator()
        }else{
            if (!hasInternet){
                Image(
                    painter = painterResource(id =
                    if (MaterialTheme.colorScheme.background == White){
                        R.drawable.ic_no_network_dark
                    } else{
                        R.drawable.ic_no_network_light
                    }
                    ),
                    contentDescription = "Problems with wifi connection"
                )
            }else{
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .clickable {
                        onExploreClicked()
                    },
                text = btnText,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Red
            )
        }

    }
}