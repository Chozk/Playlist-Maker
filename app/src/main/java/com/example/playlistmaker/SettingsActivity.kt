package com.example.playlistmaker

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.net.toUri
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {
    private lateinit var themeSwitcher: SwitchMaterial
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("darkTheme", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_settings)

        themeSwitcher = findViewById(R.id.themeSwitcher1)
        themeSwitcher.isChecked = sharedPreferences.getBoolean("darkTheme", false)
        themeSwitcher.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPreferences.edit()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("darkTheme", true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("darkTheme", false)
            }
            editor.apply()
        }

        val backButton = findViewById<ImageView>(R.id.arrow_back)
        backButton.setOnClickListener {
            finish()
        }

        val shareButton = findViewById<TextView>(R.id.shareButton)
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_url)
            )
            startActivity(Intent.createChooser(shareIntent, null))
        }

        val supportButton = findViewById<TextView>(R.id.supportButton)
        supportButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.support_subject))
                putExtra(Intent.EXTRA_TEXT, getString(R.string.support_message))
            }
            startActivity(Intent.createChooser(emailIntent, null))
        }

        val eulaButton = findViewById<TextView>(R.id.eulaButton)
        eulaButton.setOnClickListener {
            val eulaIntent = Intent(Intent.ACTION_VIEW).apply {
                data = getString(R.string.eula_url).toUri()
            }
            startActivity(eulaIntent)
        }

    }
}