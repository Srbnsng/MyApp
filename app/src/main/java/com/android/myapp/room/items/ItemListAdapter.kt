package com.android.myapp.room.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myapp.R
import com.android.myapp.room.Item
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ItemListAdapter (var context: Context) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>(){

    override fun getItemCount() = items.size

    var items = emptyList<Item>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.ingredients.text =item.ingredients
        holder.price.text = item.price.toString() + " RON"
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.itemTitle
        val ingredients: TextView = view.itemIngredients
        val price: TextView = view.itemPrice
    }


}