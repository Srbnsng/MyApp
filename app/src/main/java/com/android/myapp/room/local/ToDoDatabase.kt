package com.android.myapp.room.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.myapp.room.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Item::class],version = 1 , exportSchema = false)
abstract class ToDoDatabase : RoomDatabase(){

    abstract fun itemDao() : ItemDao

    companion object{
        @Volatile
        private var INSTANCE : ToDoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : ToDoDatabase{

            val inst = INSTANCE
            if(inst != null){
                return inst
            }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ToDoDatabase::class.java,
                "todo_db"
            )
                .addCallback(WordDatabaseCallback(scope)).build()

            INSTANCE = instance
            return instance
        }

        private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){

            override fun onOpen(db: SupportSQLiteDatabase){
                super.onOpen(db)
                INSTANCE?.let {database -> scope.launch(Dispatchers.IO){
                    populateDatabase(database.itemDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(itemDao: ItemDao){

            itemDao.insert(Item(1,"Pizza Margarita","pizza dough, tomato sauce, mozzarella",12.0,"Pizza"))
            itemDao.insert(Item(2,"Pizza Capriciosa","pizza dough, tomato sauce, mozzarella, mushrooms, ham, corn",18.0,"Pizza"))
            itemDao.insert(Item(3,"Pizza Regina","pizza dough, tomato sauce, mozzarella, salami , ham , corn",20.0,"Pizza"))
            itemDao.insert(Item(4,"Spaghetti Carbonara","sos smantana, bacon , parmesan , spaghetti",18.0,"Pasta"))
            itemDao.insert(Item(5,"Ragu Bolognese","minced pork meat, ragu sauce ,penne",18.0,"Pasta"))
            itemDao.insert(Item(6,"Pasta Marinara","marinara sauce , garlic, onions, basil, parmesan, fusili pasta",15.50,"Pasta"))
            itemDao.insert(Item(7,"Gourmanje Burger","angus patty, tomato,iceberg salad, burger sauce, fried onion, pickles",21.0,"Burger"))
            itemDao.insert(Item(8,"BBQ Burger","angus patty, tomato, iceberg salad, burger sauce, cheddar , bacon",23.0,"Burger"))

        }
    }

}