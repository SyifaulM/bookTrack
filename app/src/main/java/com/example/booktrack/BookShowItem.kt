package com.example.booktrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.booktrack.data.AppDatabase

class BookShowItem: AppCompatActivity() {
    private lateinit var title: EditText
    private lateinit var writer: EditText
    private lateinit var publisher: EditText
    private lateinit var pages: EditText
    private lateinit var desc: EditText
    private lateinit var curr: TextView
    private lateinit var pgTotal: TextView
    private lateinit var rate: RatingBar
    private lateinit var btnBack: ImageButton
    private lateinit var btnEdit: Button
    private lateinit var btnDel: Button
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var edit_pages: CardView
    private lateinit var database: AppDatabase

    private var bookId: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_detail)

        title = findViewById(R.id.item_title)
        writer = findViewById(R.id.item_writer)
        publisher = findViewById(R.id.item_publisher)
        pages = findViewById(R.id.item_pages)
        desc = findViewById(R.id.item_desc)
        curr = findViewById(R.id.detail_cur)
        pgTotal = findViewById(R.id.detail_total_pages)
        btnBack = findViewById(R.id.back_button_detail)
        btnEdit = findViewById(R.id.edit_btn)
        btnSave = findViewById(R.id.save_btn_detail)
        btnDel = findViewById(R.id.del_btn)
        btnCancel = findViewById(R.id.cancel_btn)
        edit_pages = findViewById(R.id.edit_pages)
        rate = findViewById(R.id.detail_rate)


        database = AppDatabase.getInstance(applicationContext)
        getBook()

        val rootView = findViewById<ViewGroup>(R.id.root_detail)
        setEditTextEditable(rootView, false)

        btnSave.visibility = View.GONE
        btnCancel.visibility = View.GONE

        btnEdit.setOnClickListener(){
            setEditTextEditable(rootView, true)
            btnEdit.visibility = View.GONE
            btnCancel.visibility = View.VISIBLE
            btnDel.visibility = View.GONE
            btnSave.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener(){
            val bookId = intent.getIntExtra("intent_id", 0)
            database.bookDao()
                .updateBookDetails(
                    bookId,
                    title.text.toString(),
                    writer.text.toString(),
                    publisher.text.toString(),
                    pages.text.toString(),
                    desc.text.toString()
                )
            btnCancel.visibility = View.GONE
            btnEdit.visibility = View.VISIBLE
            btnSave.visibility = View.GONE
            btnDel.visibility = View.VISIBLE
            setEditTextEditable(rootView, false)
            finish()
        }

        edit_pages.setOnClickListener {
            showEditTextDialog(this)
        }

        btnBack.setOnClickListener(){
            val bookId = intent.getIntExtra("intent_id", 0)
            val newRate = rate.rating.toInt()
            database.bookDao().updateBookRate(bookId,newRate)//update
            finish()
        }
    }

    private fun showEditTextDialog(bookShowItem: BookShowItem) {
        val dialogView = LayoutInflater.from(bookShowItem).inflate(R.layout.edit_text, null)
        val editText = dialogView.findViewById<EditText>(R.id.edit_text)

        AlertDialog.Builder(bookShowItem)
            .setTitle("Enter The Last Page You Read")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                //cek apakah input > total pages
                if (editText.text.toString().toInt()>pages.text.toString().toInt()){
                    Toast.makeText(
                        applicationContext,
                        "No Halaman Terlalu Besar, input Kembali",
                        Toast.LENGTH_SHORT).show()
                    curr.text.toString() //tidak akan terupdate
                } else {
                    curr.text = editText.text.toString()
                    val bookId = intent.getIntExtra("intent_id", 0)
                    database.bookDao().updateCurrPage(bookId,curr.text.toString().toInt())//update
                }
                //logic rate ketika halaman yang dibaca = total halaman buku
                if (editText.text.toString().toInt()==pages.text.toString().toInt()){
                    rate.setIsIndicator(false)
                    val layout = findViewById<ConstraintLayout>(R.id.root_detail)
                    layout.invalidate()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun getBook() {
        bookId = intent.getIntExtra("intent_id",0)
        val book = database.bookDao().findById(bookId)[0]
        title.setText(book.book_title)
        writer.setText(book.book_writer)
        publisher.setText(book.book_pub)
        pgTotal.text = book.book_page
        pages.setText(book.book_page)
        desc.setText(book.book_desc)
        curr.text = book.curr_page.toString()

        btnDel.setOnClickListener() {
            database.bookDao().deleteById(bookId)
            finish()
        }

    }

    private fun setEditTextEditable(view: View, editable: Boolean) {
        if (view is EditText) {
            view.isFocusable = editable
            view.isFocusableInTouchMode = editable
            view.isClickable = editable
            view.isLongClickable = editable
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                setEditTextEditable(view.getChildAt(i), editable)
            }
        }
    }

}

