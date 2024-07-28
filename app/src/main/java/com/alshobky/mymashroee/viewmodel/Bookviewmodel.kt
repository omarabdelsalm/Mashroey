package com.alshobky.mymashroee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alshobky.mymashroee.Room.BookEntity
import com.alshobky.mymashroee.repository.Repository
import kotlinx.coroutines.launch

class BookViewModel(val repository:Repository) :ViewModel(){
    fun AddBooks(book:BookEntity){
        viewModelScope.launch{
         repository.addBookToRoom(book)
        }
    }
  val books=repository.getAllbooks()
    fun deleteBooks(book:BookEntity){
        viewModelScope.launch{
            repository.deleteFeomRoom(book)
        }
    }
    fun updateBook(book:BookEntity){
        viewModelScope.launch{
            repository.updateBook(book)
        }
    }
}