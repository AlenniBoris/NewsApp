package com.example.newsapp.navigation

sealed class Route(
    val route: String
) {

    data object AllNewsRoute : Route("all_news_screen")

    data object BookmarksRoute : Route("bookmarks_screen")

    data object DetailsRoute : Route("details_screen")

}