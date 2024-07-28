package com.alshobky.mymashroee.repository

import com.alshobky.mymashroee.Room.BookDAO
import com.alshobky.mymashroee.Room.BookEntity
import com.alshobky.mymashroee.Room.BooksDB

//جلب داتا بيس والتحكم فيها من خلال ريبوسيتورى
class Repository(val booksDB: BooksDB) {
    suspend fun addBookToRoom(bookEntity:BookEntity){
       // booksDB.AddBook(bookEntity)
       booksDB.bookDAO().AddBook(bookEntity)
    }
    fun getAllbooks()=booksDB.bookDAO().getAllBooks()
    suspend fun deleteFeomRoom(bookEntity:BookEntity){
        // booksDB.AddBook(bookEntity)
        booksDB.bookDAO().deleteBook(bookEntity)
    }
    suspend fun updateBook(bookEntity:BookEntity){
        // booksDB.AddBook(bookEntity)
        booksDB.bookDAO().updateBook(bookEntity)
    }
}