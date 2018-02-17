package com.joselbe.madridshops.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.joselbe.domain.interactor.CodeClosure
import com.joselbe.domain.interactor.ErrorClousure
import com.joselbe.domain.interactor.TypeObjects
import com.joselbe.domain.interactor.deleteallshops.deleteAllShopsImpl
import com.joselbe.domain.interactor.getallshops.GetAllShopsInteractor
import com.joselbe.madridshops.R
import com.joselbe.madridshops.router.Router
import com.joselbe.madridshops.tools.hasConnection

import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.toast

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        //inernet Control
        internetControl()

        //Shops
        buttonMenuShops.setOnClickListener {
            Router().navigateFromMenuToShops(this)
        }

        //Events
        buttonMenuActivities.setOnClickListener {
            Router().navigateFromMenuToEvents(this)

        }

        //Delete all caches
        buttonMenuDeleteCache.setOnClickListener {
            val deleteInteractors : deleteAllShopsImpl  = deleteAllShopsImpl(TypeObjects.SHOPS, this)

            disableButtons()

            //delete Shops and Events
            deleteInteractors.execute( object: CodeClosure {
                override fun invoke() {
                    toast("Borrada Cache Correctamente")
                    enableButtons()
                }

            }, object : ErrorClousure{
                override fun invoke(msg: String) {
                    toast("Error al borrar las caches: " +msg)
                    enableButtons()
                }
            })
        }
    }


    private fun enableButtons(){
        buttonMenuShops.isEnabled = true
        buttonMenuActivities.isEnabled = true
    }

    private fun disableButtons(){
        buttonMenuShops.isEnabled = false
        buttonMenuActivities.isEnabled = false
    }


    // Control of Internet with the UI
    private fun internetControl(){
        disableButtons()

        //Si no hay conexión
        if (hasConnection(this) == true){
            enableButtons()
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
