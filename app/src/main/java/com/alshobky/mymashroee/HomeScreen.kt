package com.alshobky.mymashroee

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alshobky.mymashroee.Room.BooksDB
import com.alshobky.mymashroee.repository.Repository
import com.alshobky.mymashroee.viewmodel.BookViewModel

@Composable
fun HomeScreen(){
    val context= LocalContext.current
    val db=BooksDB.getInstance(context)
    val repository=Repository(db)
    val myviewModel=BookViewModel(repository)
    val navController =rememberNavController()
    NavHost(navController = navController, startDestination = "Main" ){
        composable("Main")
        {
            MainScreen(viewModel = myviewModel, navController =navController )
        }
        composable("Card/{bookId}/{bookName}")
        {
            CardScreen(navController =navController ,
                viewModel = myviewModel,
                bookId = it.arguments?.getString("bookId"),
                bookName =it.arguments?.getString("bookName") )
        }
    }



}