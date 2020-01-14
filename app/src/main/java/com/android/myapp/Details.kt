package com.android.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Details : AppCompatActivity() {


    lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback{
            googleMap = it
            val location1 = LatLng(46.781725, 23.617687)
            googleMap.addMarker(MarkerOptions().position(location1).title("Gourmanje' Restaurant"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,15f))
        })
    }

}
