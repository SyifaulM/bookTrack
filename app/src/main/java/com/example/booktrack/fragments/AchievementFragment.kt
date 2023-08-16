package com.example.booktrack.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booktrack.BookShowItem
import com.example.booktrack.R
import com.example.booktrack.adapter.BookAdapter
import com.example.booktrack.data.AppDatabase
import com.example.booktrack.data.entity.Book


class AchievementFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var list = mutableListOf<Book>()
    private lateinit var adapter: BookAdapter
    private lateinit var database: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_achievement, container, false)
        recyclerView = view.findViewById(R.id.finished_list)

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
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,true)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL))
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.bookDao().getAllBooksWhereCurrEqualsPage())
        adapter.notifyDataSetChanged()
    }

}

