package com.example.newsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.allnews.views.AllNewsScreen
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btmnav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        btmnav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homepage -> { Toast.makeText(this, "homepage", Toast.LENGTH_SHORT).show() }
                else -> { Toast.makeText(this, "bookmarks", Toast.LENGTH_SHORT).show() }
            }
            true
        }
    }
}