package com.example.booktrack.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.booktrack.BookAddManual
import com.example.booktrack.BookShowItem
import com.example.booktrack.R
import com.example.booktrack.adapter.BookAdapter
import com.example.booktrack.adapter.CurrentAdapter
import com.example.booktrack.data.AppDatabase
import com.example.booktrack.data.entity.Book


class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var list = mutableListOf<Book>()
    private lateinit var adapter: CurrentAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.current_recview)

        database = AppDatabase.getInstance(requireContext())

        setUpRecyclerView()
        return view
    }


    private fun setUpRecyclerView() {
        adapter = CurrentAdapter(list, object : CurrentAdapter.OnAdapterListener{
            override fun onClick(book: Book) {
                val intent = Intent(requireContext(), BookShowItem::class.java)
                    .putExtra("intent_id", book.id)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL ,true)
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

    //add button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addButton = view.findViewById<ImageButton>(R.id.add_btn)
        val clickForDetail = view.findViewById<CardView>(R.id.curr_card)

        addButton.setOnClickListener {
            showPopupMenu(addButton)
        }

//        clickForDetail.setOnClickListener{
//
//        }
    }

    //show menu of add button
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
