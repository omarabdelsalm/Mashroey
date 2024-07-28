package com.alshobky.mymashroee

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alshobky.mymashroee.Room.BookEntity
import com.alshobky.mymashroee.viewmodel.BookViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel:BookViewModel,navController: NavController){
   // initial variable to input data and design main screen
    var inputBook by remember{ mutableStateOf("") }
    val emty by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val books by viewModel.books.collectAsState(initial = emptyList())
    Scaffold(
        topBar={
            TopAppBar(
                title = {
                        Text(
                            text="Books Name",
                            fontWeight = FontWeight.Bold,
                            color=MaterialTheme.colorScheme.background
                        )
                },
                colors=TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
                )
        },
        floatingActionButton = {
            FloatingActionButton(
                  onClick = {showDialog.value=true},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background)
            { 
        Icon(painter = painterResource(id = R.drawable.baseline_add_24) , contentDescription = null)
            }
        }
    ) {
        if(books.isEmpty()){
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center,
               modifier = Modifier
                   .padding(it)
                   .fillMaxSize()
           ) {
              Text(text = "No Name available")
           }
        }else{
            LazyColumn (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ){
             items(books){
                 Card(
                     onClick = {
                              navController.navigate(
                                  "Card/${ Uri.encode(it.id.toString())}/${Uri.encode(it.title)}"
                              )
                     },
                     modifier = Modifier
                         .padding(5.dp)
                         .fillMaxWidth(),
                     elevation = CardDefaults.cardElevation(4.dp)
                 ){
                 Row{
                     Text(
                         text=""+it.id,
                         modifier =Modifier.padding(14.dp),
                         fontWeight = FontWeight.Bold,
                         fontSize = 24.sp,
                         color=MaterialTheme.colorScheme.primary
                     )
                     Text(
                         text=""+it.title,
                         modifier =Modifier.padding(14.dp),
                         fontWeight = FontWeight.Bold,
                         fontSize = 24.sp
                     )
                 }
                 }
             }
            }
        }
    }
    if(showDialog.value){
        AlertDialog(
            onDismissRequest = {showDialog.value=false },
            dismissButton = {
                            Button(
                                onClick = {
                                showDialog.value=false
                                inputBook=emty
                            }
                            ) {
                                Text(text = "Cancel")
                            }
            },
            confirmButton = {
                if (!inputBook.isEmpty()) {
                Button(
                    onClick = {

                        viewModel.AddBooks(BookEntity(0, inputBook))
                        showDialog.value = false
                        inputBook = emty
                    }
                        )
                    {
                       Text(text = "Save")
                      }
                    }

            },
            title = {
                Text(
                    text="Add Book Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(5.dp)
                )
            },
            text={
                OutlinedTextField(
                    value =inputBook ,
                    onValueChange = {inputBook = it},
                    label = { Text(text = "Book Name")},
                    placeholder = { Text(text = "Enter your Book Name")}
                )

            }
            )
    }

}