package com.example.playlistmaker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<Button>(R.id.searchButton)
        val mediaButton = findViewById<Button>(R.id.mediaButton)
        val settingsButton = findViewById<Button>(R.id.settingsButton)

        //кнопка поиска
        searchButton.setOnClickListener {
            val searchIntent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(searchIntent)
        }

        //кнопка медиа
        mediaButton.setOnClickListener {
            val mediaIntent = Intent(this@MainActivity, MediaActivity::class.java)
            startActivity(mediaIntent)
        }

        //кнопка настроек
        settingsButton.setOnClickListener {
            val settingsIntent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }
    }
}

