package com.android.myapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (
    @PrimaryKey @ColumnInfo (name="_id") val _id:Int,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name= "ingredients") var ingredients:String,
    @ColumnInfo(name= "price") var price:Double,
    @ColumnInfo(name= "type") var type:String
    ){

    override fun toString() :String{
        return "Item(_id='$_id', name='$name', ingredients='$ingredients', price='$price', type='$type')"
    }
}
