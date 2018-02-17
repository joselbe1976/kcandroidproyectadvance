package com.joselbe.madridshops.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.joselbe.domain.model.Shop
import com.joselbe.domain.model.Shops
import com.joselbe.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class ListaAdapter(val context: Context, val shops: Shops) : BaseAdapter() {

    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //componemos la vista de la celda personalizada

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.layout_cell_shop, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        //asignamos a la vista
        vh.titulo.text = shops.get(position).name
        vh.subtitulo.text = shops.get(position).OpenHours

        //Image with Picasso

        Picasso.with(context).load(shops.get(position).logo)
                .into(vh.imagen)

        return view!!

    }

    override fun getItem(position: Int): Shop? {
        val shop = shops.get(position)
        return shop
    }

    override fun getItemId(position: Int): Long = 0
    override fun getCount() = shops.count()


    //Clase Holder
    internal class ListRowHolder(row: View?) {
        public val titulo: TextView
        public val subtitulo: TextView
        public val imagen: ImageView

        init {

            titulo = row?.findViewById<TextView>(R.id.TextCell1)!!
            subtitulo = row?.findViewById<TextView>(R.id.TextCell2)!!
            imagen = row?.findViewById<ImageView>(R.id.ImageCell1)!!

        }
    }

}