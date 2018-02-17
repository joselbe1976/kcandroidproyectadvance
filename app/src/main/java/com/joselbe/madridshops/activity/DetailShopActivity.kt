package com.joselbe.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.joselbe.domain.model.Shop
import com.joselbe.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_shop.*

class DetailShopActivity : AppCompatActivity() {



    companion object {
        val PUT_EXTRA_DATA  = "PUT_EXTRA_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_shop)


        val shop = intent.getSerializableExtra(PUT_EXTRA_DATA) as Shop

        // The Texts
        txtDetailTitle.text = shop.name
        txtDetailAddress.text = shop.address
        txtDetailComments.text = shop.description
        txtDetailOpeningHours.text = shop.OpenHours


        //generate The URL
        var urlMap : String  = "http://maps.googleapis.com/maps/api/staticmap?zoom=17&size=320x220&scale=2&markers=%7Ccolor:0x9C7B14%7C"
        urlMap = urlMap + shop.lat_gps + "," + shop.long_gps

        Log.d("Shops", urlMap)

        //The Image download with Picasso
        Picasso
                .with(this)
                .load(urlMap)
                .into(imageDetailMap)
    }
}
