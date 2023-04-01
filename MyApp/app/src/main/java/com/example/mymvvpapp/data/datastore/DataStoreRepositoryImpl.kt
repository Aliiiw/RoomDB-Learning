package com.example.mymvvpapp.data.datastore

import android.content.Context
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {

    override suspend fun putString(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override suspend fun putInt(key: String, value: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getString(key: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getInt(key: String): Int? {
        TODO("Not yet implemented")
    }

}