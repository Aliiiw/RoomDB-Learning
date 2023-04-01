package com.example.mymvvpapp.data.repository

import com.example.mymvvpapp.data.network.ApiInterface
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: ApiInterface
) {
    suspend fun getAllPosts() = api.getAllPosts()
}