package com.joselbe.madridshops.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.joselbe.domain.model.Shop
import com.joselbe.madridshops.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_cell_shop.view.*


class InfoWindowAdapterMaps(val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_map_windows, null)

        if (p0?.tag is Shop) {
            val shop = p0.tag as Shop

            view.TextCell1.text = shop.name
            view.TextCell2.text = shop.OpenHours
            //the image load
            val image = view.ImageCell1
            Picasso
                    .with(context)
                    .load(shop.logo)
                    .into(image, MarkerCallback(p0, shop.logo, image, context))
        }

        return view
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null

    }


}

class MarkerCallback(val marker: Marker,
                     val url: String,
                     val imageView: ImageView,
                     val context: Context): Callback {

    override fun onSuccess() {
        if (marker.isInfoWindowShown) {
            marker.hideInfoWindow()

            Picasso
                    .with(context)
                    .load(url)
                    .into(imageView)

            marker.showInfoWindow()
        }
    }

    override fun onError() { Log.d("Error", "Error al mostrar") }
}

