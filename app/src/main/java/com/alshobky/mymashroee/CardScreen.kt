package com.alshobky.mymashroee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CardScreen(
    navController:NavController,
    viewModel: BookViewModel,
    bookId:String?,
    bookName:String?
)
{
   val showDialog= remember { mutableStateOf(false)}
    // for update function
    val updateDialog= remember { mutableStateOf(false)}
    var inputBook by remember{ mutableStateOf("") }
    val emty by remember { mutableStateOf("") }
    // column to show element
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // card view
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(15.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ){
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Text(
                        text ="Id:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color= MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text =""+bookId,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,

                    )
                }
                Row {
                    Text(
                        text ="Name:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color= MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text =""+bookName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,

                        )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)


                ){
                    OutlinedButton(
                        onClick = { showDialog.value = true },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(
                            height = 100.dp,
                            width = 130.dp)
                        )
                    {
                        Row {
                           Icon(
                               painter = painterResource(id = R.drawable.baseline_delete_24),
                               contentDescription = null
                           )
                            Text(text = "Delete", fontSize = 20.sp)
                        }
                    }
                    OutlinedButton(
                        onClick = {updateDialog.value=true },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier.size(height = 100.dp, width = 130.dp)
                    )
                    {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_edit_24),
                                contentDescription = null
                            )
                            Text(text = "Update",
                                fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
    // for Delete items
 if(showDialog.value){
     AlertDialog(
         onDismissRequest = {showDialog.value=false },
         dismissButton = {
                         Button(onClick = { showDialog.value=false })
                         {
                             Text(text = "No")
                         }
         },
         confirmButton = {
            Button(onClick = {
                if(bookId!=null){
                    viewModel.deleteBooks(book= BookEntity(id=bookId.toInt(),
                        title = bookName.toString()))
                }
                showDialog.value=false
                // for return to main screen
                navController.popBackStack()
            }) {
                Text(text = "Yes")
            }
         },
         title = {
             Text(text = "Delete Book",
                 fontWeight = FontWeight.Bold,
                 fontSize = 25.sp
                 )
         },
         text={
             Text(text = "Are You sure ?",
                 fontSize = 20.sp)
         }
     )
 }
    // for update items
    if(updateDialog.value){
        AlertDialog(
            onDismissRequest = {updateDialog.value=false },
            dismissButton = {
                Button(
                    onClick = {
                        updateDialog.value=false
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
                            val newBook=BookEntity(bookId!!.toInt(),inputBook)

                            viewModel.updateBook(newBook)
                            // for return to main screen
                            navController.popBackStack()
                            updateDialog.value = false
                            inputBook = emty
                        }
                    )
                    {
                        Text(text = "Update")
                    }
                }

            },
            title = {
                Text(
                    text="update Book Name",
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