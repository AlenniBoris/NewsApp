package com.example.newsapp.navigation

import com.example.newsapp.R

sealed class Screen(
    val route: String,
    val activeIcon: Int? = null,
    val notActiveIcon: Int? = null,
) {

    data object AllNewsScreen : Screen(
        "all_news_screen",
        R.drawable.icon_all_news_active,
        R.drawable.icon_all_news
    )

    data object BookmarksScreen : Screen(
        "bookmarks_screen",
        R.drawable.icon_bookmarks_active,
        R.drawable.icon_bookmarks
    )

    data object DetailsScreen : Screen(
        "details_screen"
    )

}