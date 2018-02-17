package com.joselbe.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.joselbe.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this).isLoggingEnabled = true

        Picasso.with(this).load("http://stillcracking.com/wp-content/uploads/2014/05/c5731d46405fb1226cad6eef30c99ce145e7894eb7948b4180e78e0b3a32aaca_1.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img1)

        Picasso.with(this).load("http://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2017/05/28/14959850455727.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img2)

        Picasso.with(this).load("http://ocio.diarioinformacion.com/img_contenido/noticias/2017/01/557332/carmen.jpg")
                .placeholder(android.R.drawable.ic_delete)
                .into(img3)

    }
}
