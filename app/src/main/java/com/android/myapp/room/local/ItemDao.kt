package com.android.myapp.room.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.myapp.room.Item

@Dao
interface ItemDao {
    @Query("Select * from items")
    fun getAll():LiveData<List<Item>>

    @Query("Select * from items where _id=:id ")
    fun getById(id: Int) : LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Query("delete from items")
    suspend fun deleteAll()

}