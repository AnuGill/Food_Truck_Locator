package com.android.example.foodtrucklocator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    //private lateinit var mMap: GoogleMap
    internal var map: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val foodtruck1 =
            LatLng(34.121178, -118.200265)
        val ArandinosTacosElPatron =
            LatLng(34.083192, -118.092952)
        val LosCaliforniaTacos =
            LatLng(34.141236, -118.150571)
        val ArturoTacoTruck =
            LatLng(34.138954, -118.150195)
        val SantaRitaJaliscoTacoTruck =
            LatLng(34.035913, -118.181511)
        map!!.addMarker(MarkerOptions().position(foodtruck1).title("foodtruck1"))
        map!!.addMarker(
            MarkerOptions().position(
                ArandinosTacosElPatron
            ).title("ArandinosTacosElPatron")
        )
        map!!.addMarker(
            MarkerOptions().position(LosCaliforniaTacos).title(
                "LosCaliforniaTacos"
            )
        )
        map!!.addMarker(
            MarkerOptions().position(ArturoTacoTruck).title(
                "ArturoTacoTruck"
            )
        )
        map!!.addMarker(
            MarkerOptions().position(
                SantaRitaJaliscoTacoTruck
            ).title("SantaRitaJaliscoTacoTruck")
        )
        map!!.animateCamera(CameraUpdateFactory.newLatLng(foodtruck1))
        map!!.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                foodtruck1,
                10f
            )
        )
        map!!.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                ArandinosTacosElPatron,
                10f
            )
        )
        map!!.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LosCaliforniaTacos,
                10f
            )
        )
        map!!.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                ArturoTacoTruck,
                10f
            )
        )
        map!!.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                SantaRitaJaliscoTacoTruck,
                10f
            )
        )



    }
}
