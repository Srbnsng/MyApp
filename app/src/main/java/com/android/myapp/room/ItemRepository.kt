package com.android.myapp.room

import androidx.lifecycle.LiveData
import com.android.myapp.core.Result
import com.android.myapp.room.local.ItemDao
import com.android.myapp.room.remote.ItemApi

class ItemRepository(private val itemDao:ItemDao){

    val items = itemDao.getAll()

    suspend fun refresh():Result<Boolean>{
        try{
            val items = ItemApi.service.find()
            for(item in items){
                itemDao.insert(item)
            }
            return Result.Success(true)
        }
        catch (e:Exception){
            return Result.Error(e)
        }
    }

    fun getById(itemId : Int) : LiveData<Item>{
        return itemDao.getById(itemId)
    }


}