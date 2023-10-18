package com.venu.livedataupdate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.venu.livedataupdate.repository.Repository
import com.venu.livedataupdate.roomDatabase.database.UserDataBase
import com.venu.livedataupdate.ui.theme.LiveDataUpdateTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveDataUpdateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    //val viewModel: MainViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    val db = UserDataBase.getInstance(context)
    val userDao = db.getUserDao()
    val repository = Repository(userDao)
    val viewModel = MainViewModel(repository)

    var name by remember {
        mutableStateOf("")
    }
    var location by remember {
        mutableStateOf("")
    }
    var mobileNumber by remember {
        mutableStateOf("")
    }
    val userData by viewModel.allUserDetails.observeAsState(emptyList())

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, placeholder = { Text(
            text = "Name"
        )})
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(value = location,
            onValueChange = { location = it }, placeholder = { Text(text = "Location")})
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it }, placeholder = { Text(text = "Mobile Number")})
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {
            scope.launch {
                viewModel.insertUser(name,mobileNumber,location)
            }
        }) {
            Text(text = "Add User")
        }

        Column(Modifier.verticalScroll(rememberScrollState())) {
            userData.forEach { user ->
                Text(text = "${user.userName} - ${user.location} - ${user.mobileNumber} ")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiveDataUpdateTheme {
        Greeting("Android")
    }
}