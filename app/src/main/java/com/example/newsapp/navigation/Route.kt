package com.example.newsapp.navigation

sealed class Route(
    val route: String
) {

    object AllNewsRoute : Route("all_news_screen")

    object BookmarksRoute : Route("bookmarks_screen")

//    object DetailsRoute : Route("details_screen/{article}")
    object DetailsRoute : Route("details_screen")

}