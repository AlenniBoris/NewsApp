package com.example.newsapp.presentation.allnews.views

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.newsapp.utils.Constants
import com.example.newsapp.presentation.allnews.AllNewsScreenViewModel

@Composable
fun AllNewsTabScreen(
    viewModel: AllNewsScreenViewModel,
    queryTab: String,
) {
    var tabIndex by remember {
        mutableStateOf(
            if (Constants.TABS.contains(queryTab)) Constants.TABS.indexOf(
                queryTab
            ) else 0
        )
    }

    ScrollableTabRow(selectedTabIndex = tabIndex) {
        Constants.TABS.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = tabIndex == index,
                onClick = {
                    viewModel.getNewsByQuery(title)
                    tabIndex = index
                },
            )
        }
    }
}