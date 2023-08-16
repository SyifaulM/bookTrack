package com.example.booktrack.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.booktrack.BookAddManual
import com.example.booktrack.BookShowItem
import com.example.booktrack.R
import com.example.booktrack.adapter.BookAdapter
import com.example.booktrack.adapter.CurrentAdapter
import com.example.booktrack.data.AppDatabase
import com.example.booktrack.data.entity.Book


class BookShelfFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var list = mutableListOf<Book>()
    private lateinit var adapter: BookAdapter
    private lateinit var database: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_shelf, container, false)
        recyclerView = view.findViewById(R.id.book_list)

        database = AppDatabase.getInstance(requireContext())
        setUpRecyclerView()
        return view
    }

    private fun setUpRecyclerView() {
        adapter = BookAdapter(list, object : BookAdapter.OnAdapterListener{
            override fun onClick(book: Book) {
                val intent = Intent(requireContext(), BookShowItem::class.java)
                    .putExtra("intent_id", book.id)
                startActivity(intent)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,true)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), HORIZONTAL))
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.bookDao().getAll())
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addButton = view.findViewById<ImageButton>(R.id.add_btn)

        addButton.setOnClickListener {
            showPopupMenu(addButton)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
//                R.id.action_add_online -> {
//                    // Navigate to BookAddOnline activity
//                    val intent = Intent(requireContext(), BookAddOnline::class.java)
//                    startActivity(intent)
//                    true
//                }
                R.id.action_add_manual -> {
                    val intent = Intent(requireContext(), BookAddManual::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}