package com.example.booktrack

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.room.RoomDatabase
import com.example.booktrack.data.AppDatabase
import com.example.booktrack.data.entity.Book

class BookAddManual : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var title: EditText
    private lateinit var writer: EditText
    private lateinit var publisher: EditText
    private lateinit var pages: EditText
    private lateinit var desc: EditText
    private lateinit var btnAdd: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_add)

        title = findViewById(R.id.input_title)
        writer = findViewById(R.id.input_writer)
        publisher = findViewById(R.id.input_publisher)
        pages = findViewById(R.id.input_pages)
        desc = findViewById(R.id.input_desc)
        btnAdd = findViewById(R.id.add_book)

        database = AppDatabase.getInstance(applicationContext)

        btnAdd.setOnClickListener {
            if (title.text.isNotEmpty() && writer.text.isNotEmpty() && pages.text.isNotEmpty()) {
                database.bookDao().insertAll(
                    Book(
                        null,
                        title.text.toString(),
                        writer.text.toString(),
                        publisher.text.toString(),
                        pages.text.toString(),
                        0,
                        desc.text.toString(),
                        0
                    )
                )
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Terdapat Data Penting Yang Belum Diisi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}