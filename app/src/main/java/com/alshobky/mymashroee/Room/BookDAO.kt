package com.alshobky.mymashroee.Room
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao

interface BookDAO{

  @Insert
  suspend fun AddBook(bookEntity: BookEntity)
  @Query("SELECT * FROM BookEntity")
  fun getAllBooks() : Flow<List<BookEntity>>
  @Delete
  suspend fun deleteBook(bookEntity: BookEntity)
  @Update
  suspend fun updateBook(bookEntity: BookEntity)
}