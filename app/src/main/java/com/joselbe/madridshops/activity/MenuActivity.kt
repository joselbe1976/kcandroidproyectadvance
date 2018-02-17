package com.joselbe.madridshops.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.joselbe.madridshops.R
import com.joselbe.madridshops.router.Router
import com.joselbe.madridshops.tools.hasConnection

import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        //inernet Control
        internetControl()

        //Shops
        buttonMenuShops.setOnClickListener {
            val router  =  Router()
            router.navigateFromMenuToShops(this)

        }

        //Activities
        buttonMenuActivities.setOnClickListener {


        }

    }

    // Control of Internet with the UI
    private fun internetControl(){
        buttonMenuShops.visibility = View.INVISIBLE
        buttonMenuActivities.visibility = View.INVISIBLE

        //Si no hay conexión
        if (hasConnection(this) == true){
            buttonMenuShops.visibility = View.VISIBLE
            buttonMenuActivities.visibility = View.VISIBLE
        }
        else{
            AlertDialog.Builder(this)
                    .setTitle("No internet Connection")
                    .setMessage("¿Desea comprobar Conexion Internet?")
                    .setPositiveButton("Si", { dialog, which ->
                        dialog.dismiss()
                        internetControl() //recursive Call
                    })
                    .setNegativeButton("No", { dialog, which ->
                        finish()
                    })
                    .show()
        }
    }


}
