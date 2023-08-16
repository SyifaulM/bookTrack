package com.example.booktrack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.booktrack.R
import com.example.booktrack.data.entity.Book

class CurrentAdapter(var list: List<Book>, private val listener: OnAdapterListener) : RecyclerView.Adapter<CurrentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title: TextView
        var writer: TextView
        var cur: TextView
        var total_page: TextView
        lateinit var curr_card: CardView

        init {
            title = view.findViewById(R.id.cur_book_title)
            writer = view.findViewById(R.id.cur_writer)
            total_page = view.findViewById(R.id.total_pages)
            cur = view.findViewById(R.id.cur_page)
            curr_card = view.findViewById(R.id.curr_card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_current, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = list[position]
        holder.title.text = book.book_title
        holder.writer.text = book.book_writer
        holder.total_page.text = book.book_page
        holder.cur.text = book.curr_page.toString()
        //to get data when a card being clicked
        holder.curr_card.setOnClickListener{
            listener.onClick(book)
        }
    }

    interface OnAdapterListener{
        fun onClick(book: Book)
    }
}