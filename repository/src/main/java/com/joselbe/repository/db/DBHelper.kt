package com.joselbe.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

fun buildDBHelper(context : Context, name: String, version: Int) : DBHelper{
    return DBHelper(context, name,  null, version)
}


class DBHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)

        //active CASCADE DELETION == ON
        db?.setForeignKeyConstraintsEnabled(true)  //es una conexion a la base de datos y se activa esto para que funcionen los borrados en cascada

    }
    override fun onCreate(db: SQLiteDatabase?) {
        //execute all sentences SQL of Creation
        DBConstants.CREATE_DATABASE_SCRIPTS.forEach { db?.execSQL(it)  }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1 && newVersion == 2){
            //Scripts de actualizacion
        }
    }

}


// helpers

fun convert(boolean : Boolean) : Int {
    when (boolean) {
        true -> return 1
        false -> return 0
        else -> return 0
    }
}

fun convert(int : Int) : Boolean {
    when (int) {
        0 -> return false
        1 -> return true
        else -> return true

    }
}