package com.android.myapp.room.items

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.myapp.core.Result
import com.android.myapp.core.TAG
import com.android.myapp.room.Item
import com.android.myapp.room.ItemRepository
import com.android.myapp.room.local.ToDoDatabase
import kotlinx.coroutines.launch

class ItemViewModel(application:Application) : AndroidViewModel(application){

    val items: LiveData<List<Item>>
    val itemRepository : ItemRepository

    init{
        val itemDao = ToDoDatabase.getDatabase(application,viewModelScope).itemDao()
        itemRepository = ItemRepository(itemDao)
        items = itemRepository.items
    }

    fun refresh(){
        viewModelScope.launch {
            when (val result = itemRepository.refresh()){
                is Result.Success -> {
                    Log.d(TAG, "refresh succeeded")
                }
                is Result.Error -> {
                    Log.w(TAG,"refresh failed", result.exception)
                }
            }
        }
    }
}