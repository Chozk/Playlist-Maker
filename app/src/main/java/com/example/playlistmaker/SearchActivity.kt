package com.example.playlistmaker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SearchActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var clearButton: ImageView
    private var searchText: String = ""

    companion object {
        private const val SEARCH_TEXT_KEY = "searchText"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Кнопка назад
        val backButton = findViewById<ImageView>(R.id.arrow_back_search)
        backButton.setOnClickListener {
            finish()
        }

        searchEditText = findViewById(R.id.search_edit_text)
        clearButton = findViewById(R.id.clear_button)

        clearButton.setOnClickListener {
            searchEditText.text.clear()
        }

        val testText = findViewById<TextView>(R.id.test_text)
        val simpleTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                testText.text = s
                clearButton.visibility = clearButtonVisibility(s)
                searchText = s.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }
        searchEditText.addTextChangedListener(simpleTextWatcher)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_TEXT_KEY, searchText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchText = savedInstanceState.getString(SEARCH_TEXT_KEY, "")
        searchEditText.setText(searchText)
    }

    private fun clearButtonVisibility(s: CharSequence?): Int {
        return if (s.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}
