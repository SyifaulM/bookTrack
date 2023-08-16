package com.example.booktrack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.booktrack.R
import com.example.booktrack.data.entity.Book

class BookAdapter(var list: List<Book>, private val listener: OnAdapterListener) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title: TextView
        var writer: TextView
        lateinit var curr_card: CardView

        init {
            title = view.findViewById(R.id.title_book)
            writer = view.findViewById(R.id.writer_book)
            curr_card = view.findViewById(R.id.book_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = list[position]
        holder.title.text = book.book_title
        holder.writer.text = book.book_writer
        //to get data when a card being clicked
        holder.curr_card.setOnClickListener{
            listener.onClick(book)
        }
    }

    interface OnAdapterListener{
        fun onClick(book: Book)
    }
}