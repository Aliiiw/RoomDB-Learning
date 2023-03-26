package com.example.mymvvpapp


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvpapp.data.model.Post
import com.example.mymvvpapp.ui.theme.MyMVVPAppTheme
import com.example.mymvvpapp.viewmodel.PostsViewModel
import com.example.mymvvpapp.viewmodel.RandomPersonViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMVVPAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

                    ObserveRandomPersonViewModel()
                }
            }
        }
    }

    //buffer nabood: emit(1) + 100 -> collect(1) + 300 + emit(2) + 100 -> collect(2) + 300 ...
    //ba buffer: emit(1) + 100 mire collect mibine 300 ta jadare mire emit(2) hnuz collect nayomade mire emit(3) , ....


    fun myTestFlow(): Flow<Int> = flow {
        for (i in 1..10) {
            delay(100)
            emit(i)
        }
    }


    //var args migire ya taki ya har chanta
    fun sendNumbers2() = flowOf(1)

    fun sendNumbers3() = flowOf(1, 2, "Ali", true, 7.9)


    //roye list ya collection
    fun sendNumbers() = listOf(1, 2, 3, 4, 5).asFlow()


    //noyi az flow builder
    fun power2(): Flow<Int> = flow {
        for (i in 1..200) {
            delay(100)
            emit(i * i)
        }
    }


    @Composable
    fun ObserveRandomPersonViewModel() {
        val viewModel by viewModels<RandomPersonViewModel>()


        val person by viewModel.stateFlowPerson.collectAsState()

        val context = LocalContext.current

        LaunchedEffect(true) {

            viewModel.sharedFlowPerson.collectLatest {
                Toast.makeText(context, "Random Person is: $it", Toast.LENGTH_LONG).show()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Text(text = person)

            Button(onClick = {
                viewModel.getRandomPerson()
            }) {
                Text(text = "click me")
            }
        }

    }

    @Composable
    private fun ObservePostsViewModel() {

        var postsList by remember { mutableStateOf(emptyList<Post>()) }

        Column() {
            PostView(postList = postsList)
        }

        LaunchedEffect(key1 = Unit) {

            val viewModel = ViewModelProvider(this@MainActivity).get(PostsViewModel::class.java)
            viewModel.getAllPostsRequest()

            viewModel.postsList.collectLatest { posts ->
                postsList = posts
            }

            viewModel.postsListError.collectLatest { isError ->
                isError?.let {
                    Log.e("2323", isError)
                }
            }

            viewModel.loading.collectLatest { isLoading ->
                Log.e("2323", isLoading.toString())
            }
        }
    }

    @Composable
    fun PostView(postList: List<Post>) {
        LazyColumn() {
            items(postList) { post ->
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .background(Color.Blue)
                ) {
                    Text(text = post.title, color = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = post.body, color = Color.DarkGray)
                }

            }
        }
    }
}
