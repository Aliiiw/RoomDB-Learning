package com.example.mymvvpapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvpapp.data.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    companion object {
        const val USER_PHONE_KEY = "USER_PHONE_KEY"
    }

    fun saveUserPhone(phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putString(key = USER_PHONE_KEY, value = phoneNumber)
        }
    }

    //    fun getUserPhone1(): String? = runBlocking {
//        repository.getString(USER_PHONE_KEY)
//    } khub ni

    val userPhone = MutableStateFlow("")
    fun getUserPhone2() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getString(USER_PHONE_KEY)?.let {
                userPhone.emit(it)
            }
        }
    }


    suspend fun getUserPhone3(): String? = repository.getString(USER_PHONE_KEY)


}