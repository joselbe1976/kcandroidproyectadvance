package com.joselbe.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.ViewSwitcher
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.joselbe.domain.interactor.ErrorCompletion
import com.joselbe.domain.interactor.SuccessCompletion
import com.joselbe.domain.interactor.TypeObjects
import com.joselbe.domain.interactor.getallshops.GetAllShopsInteractor
import com.joselbe.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.joselbe.domain.model.Shop
import com.joselbe.domain.model.Shops
import com.joselbe.madridshops.R
import com.joselbe.madridshops.adapters.InfoWindowAdapterMaps
import com.joselbe.madridshops.fragment.ListFragment
import com.joselbe.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_shops.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.concurrent.schedule

class ShopsActivity : AppCompatActivity(), ListFragment.OnShopSelectedListener {



    var listFragmentos : ListFragment? = null

    private lateinit var viewSwitcher : ViewSwitcher
    var typeData : Int? = 0 //esto es shops or events

    companion object {
        val PUT_EXTRA_DATA_TYPE  = "PUT_EXTRA_DATA_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)
        setSupportActionBar(toolbar)

        //get the Data Type (shops or Events)
        typeData = intent.getSerializableExtra(ShopsActivity.PUT_EXTRA_DATA_TYPE) as Int

        viewSwitcher = viewSwitcher1
        viewSwitcher.displayedChild = 0
        Thread.sleep(500)

        //MAP
         setupMap(this)



    }

    private fun setupList(shops : Shops) {
        listFragmentos = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as ListFragment



        if (shops != null) {
            listFragmentos?.setShops(shops)
            viewSwitcher.displayedChild = 1  //show the activity full (map + list)
        }
    }


    private fun setupMap(context: Context) {

        val getAllShopsInteractor : GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(typeData!! , object: SuccessCompletion<Shops>{
            override fun successCompletion(shops: Shops) {
                initializeMap(shops)

                //List
                setupList(shops)

           }

        }, object: ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(context, "Error Loading Shops", Toast.LENGTH_LONG)
            }

        })
    }


    fun centerMapInPosition(map : GoogleMap, latitude : Double, longitude : Double){
        val coordinate = LatLng(latitude, longitude)
        val cameraPos = CameraPosition.builder().target(coordinate).zoom(13f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos))

        map.uiSettings!!.isZoomControlsEnabled = true
    }

    fun showUserPosition(conext : Context, map : GoogleMap){
        if (ActivityCompat.checkSelfPermission(conext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(conext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){

            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 10)
            return
        }


    }

    private var map: GoogleMap? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //cuando se dice ok no en usuario en permisos
        if (requestCode == 10){
            try {
                map?.isMyLocationEnabled = true
            }
            catch(e: SecurityException){

            }
        }
    }

    fun addPin(map: GoogleMap, shop: Shop)
    {
        map?.addMarker(MarkerOptions()
                .position(LatLng(shop.lat_gps.toDouble(), shop.long_gps.toDouble()))
                .title(shop.name)
                .snippet(shop.OpenHours))
                .tag = shop

    }




    fun addAllPins(shops : Shops){
        for (i in 0..shops.count()-1){
            val shop = shops.get(i)


            if (shop.lat_gps.trim().length > 0) {
                Log.d("Shops", shop.lat_gps + " - " + shop.long_gps)
                this.addPin(this.map!!, shop)

                //Event tap
                map?.setOnInfoWindowClickListener {
                    if (it.tag is Shop) {
                        val shopAux : Shop = it.tag as Shop
                        Router().navigateToDetail(this, shopAux)
                    }
                }
            }
        }

    }

    private fun initializeMap(shops: Shops){
        val mapFragment =  supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({

            this.map = it

            centerMapInPosition(it, 40.416775, -3.70)
            showUserPosition(this, it)
            addAllPins(shops)
            it.setInfoWindowAdapter(InfoWindowAdapterMaps(this))


        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        return true
    }


    override fun onShopSelected(shop: Shop?) {
        Router().navigateToDetail(this, shop!!)
    }
}
