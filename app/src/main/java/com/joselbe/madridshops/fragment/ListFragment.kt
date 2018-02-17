package com.joselbe.madridshops.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.joselbe.domain.model.Shop
import com.joselbe.domain.model.Shops

import com.joselbe.madridshops.R
import com.joselbe.madridshops.adapters.ListaAdapter


class ListFragment : Fragment() {



    //Delegado
    interface OnShopSelectedListener {
        fun onShopSelected(shop: Shop?)
    }


    lateinit var root: View
    lateinit var list: ListView
    lateinit var _shops: Shops

    //Para comunicar Fragment con Activity
    private var onShopSelectedListener: OnShopSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        root = inflater!!.inflate(R.layout.fragment_list, container, false)
        return root
    }


    fun setShops(shops : Shops){
           _shops = shops

            list = root.findViewById<ListView>(R.id.table_list)
            list.adapter = ListaAdapter(activity, shops) //creo el adaptador


            // Nos enteramos de que se ha pulsado un elemento de la lista así:
            list.setOnItemClickListener { parent, view, position, id ->
                // Aviso al listener
                onShopSelectedListener?.onShopSelected(shops.get(position))
            }

    }



    // Ciclo de vida
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onShopSelectedListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnShopSelectedListener) {
            onShopSelectedListener = listener
        }
    }
}
