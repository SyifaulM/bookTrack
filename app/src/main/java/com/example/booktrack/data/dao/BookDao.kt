package com.example.booktrack.data.dao

import androidx.room.*
import com.example.booktrack.data.entity.Book

//query ke entitas

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    @Query("SELECT * FROM book WHERE id IN (:bookIds)")
    fun loadAllByIds(bookIds: IntArray): List<Book>

    @Query("SELECT * FROM book WHERE id=:bookId")
    fun findById(bookId: Int): List<Book>

    @Query("SELECT * FROM book WHERE curr = page")
    fun getAllBooksWhereCurrEqualsPage(): List<Book>

    @Query("SELECT * FROM book WHERE title LIKE :title")
    fun findByTitle(title: String): Book

    @Query("DELETE FROM book WHERE id = :bookId")
    fun deleteById(bookId: Int)

    @Update
    fun update(book: Book)

    @Query("UPDATE book SET title = :newTitle, writer = :newWriter, publisher = :newPublisher, page = :newPage, desc = :newDesc WHERE id = :bookId")
    fun updateBookDetails(bookId: Int, newTitle: String, newWriter: String, newPublisher: String, newPage: String, newDesc: String)

    @Query("UPDATE book SET curr = :newCurrPage WHERE id = :bookId")
    fun updateCurrPage(bookId: Int, newCurrPage: Int)

    @Query("UPDATE book SET rate = :newRate WHERE id = :bookId")
    fun updateBookRate(bookId: Int, newRate: Int)

//    @Query("SELECT rate FROM book WHERE id = :bookId")
//    fun findRate(bookId: Int)

    @Insert
    fun insertAll(vararg books: Book)

    @Delete
    fun delete(book: Book)
}