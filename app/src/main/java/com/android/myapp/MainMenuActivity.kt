package com.android.myapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapp.room.Item
import com.android.myapp.room.items.ItemListAdapter
import com.android.myapp.room.items.ItemViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: ItemListAdapter
    private lateinit var itemViewModel : ItemViewModel
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        initRecyclerView()
        initPieChart()
    }

    private fun initRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        recyclerViewAdapter= ItemListAdapter(this)
        recycler.adapter = recyclerViewAdapter

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        itemViewModel.items.observe(this, Observer { items->
            Log.v("app","update items")
            recyclerViewAdapter.items = items
            initPieChart()
            pieChart.centerText = "Food !"
            pieChart.holeRadius = 40f
            pieChart.setCenterTextSize(12f)
            println(recyclerViewAdapter.itemCount)
        })
        itemViewModel.refresh()



    }

    private fun initPieChart(){

        pieChart = findViewById(R.id.pie)
        val values = ArrayList<PieEntry>()
        val map = mutableMapOf<String,Int>()
        val itemList = recyclerViewAdapter.items
        println("Items size :" +  itemList.size)
        for(item in itemList){
            if(!map.containsKey(item.type)){
                map[item.type] = 1
            }else{
                map[item.type] = map.getValue(item.type) + 1
            }
        }
        for(item in map){
            values.add(PieEntry(item.value.toFloat(),item.key))
        }
        val pieDataSet = PieDataSet(values,"Types of food")
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        val pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieChart.holeRadius = 55f
        pieChart.transparentCircleRadius = 20f
        pieChart.centerText = "Click me ! "
        pieChart.setCenterTextSize(17f)
        pieChart.setDescription("All foods in just a pie")
        pieChart.setDescriptionColor(2)
    }

}
