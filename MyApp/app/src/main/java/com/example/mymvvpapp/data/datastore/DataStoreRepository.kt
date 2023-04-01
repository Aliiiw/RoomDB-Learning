package com.example.mymvvpapp.data.datastore

interface DataStoreRepository {
    //key ha hamishe string hastan

    suspend fun putString(key: String, value: String)

    suspend fun putInt(key: String, value: Int)

    suspend fun getString(key: String): String?

    suspend fun getInt(key: String): Int?

}