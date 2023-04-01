package com.example.mymvvpapp.di

import android.content.Context
import com.example.mymvvpapp.data.datastore.DataStoreRepository
import com.example.mymvvpapp.data.datastore.DataStoreRepositoryImpl
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object DatastoreModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(context = context)
}