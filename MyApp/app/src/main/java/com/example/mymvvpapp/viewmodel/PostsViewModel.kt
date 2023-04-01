package com.example.mymvvpapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mymvvpapp.data.model.Post
import com.example.mymvvpapp.data.network.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val api: ApiInterface
) : ViewModel() {

    //live data
//    val postsList = MutableLiveData<List<Post>>()
//    val postsListError = MutableLiveData<String?>()
//    val loading = MutableLiveData<Boolean?>()

    //Flow
    val postsList = MutableStateFlow<List<Post>>(emptyList())
    val postsListError = MutableStateFlow<String?>("")
    val loading = MutableStateFlow<Boolean?>(true)


    fun getAllPostsRequest() {

        loading.value = true

        CoroutineScope(Dispatchers.IO).launch {

            val response = api.getAllPosts()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { allPosts ->
                        postsList.emit(allPosts)
                        postsListError.emit(null)
                        loading.emit(false)
                    }
                } else {
                    postsListError.emit(response.message())
                    loading.emit(false)
                }
            }
        }
    }
}