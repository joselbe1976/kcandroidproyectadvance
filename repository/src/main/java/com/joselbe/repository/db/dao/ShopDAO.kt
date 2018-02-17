package com.joselbe.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.joselbe.repository.db.DBConstants
import com.joselbe.repository.db.DBHelper
import com.joselbe.repository.model.ShopEntity

class ShopDAO(val type : Int, val dbHelper : DBHelper) : DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection : SQLiteDatabase = dbHelper.readableDatabase
    private val dbWriteOnlyConnection : SQLiteDatabase = dbHelper.writableDatabase

    override fun insert(element: ShopEntity): Long {
        var id: Long = -1
        id = dbWriteOnlyConnection.insert(DBConstants.TABLE_SHOP,null, contentValues(element))
        return id
    }

    override fun delete(id: Long): Long {
        return  dbWriteOnlyConnection.delete(DBConstants.TABLE_SHOP,DBConstants.KEY_SHOP_TYPE_DATA + " = ? AND " + DBConstants.KEY_SHOP_DATABASE_ID + " = ?", arrayOf(type.toString(), id.toString())).toLong()
   }
    override fun delete(element: ShopEntity): Long {
        if (element.databaseId < 1){
            return 0
        }
        return delete(element.databaseId)
    }

    //eliminamos todos, pero podemos hacerlo si quieremos por tipos SHops o EVENTS
    override fun deleteAll(): Boolean {
        return  dbWriteOnlyConnection.delete(DBConstants.TABLE_SHOP,null, null).toLong() >= 0
    }

    fun contentValues(shopEntity : ShopEntity) : ContentValues {
        val content = ContentValues()

        content.put(DBConstants.KEY_SHOP_TYPE_DATA, type) //pk shops or events
        content.put(DBConstants.KEY_SHOP_ID_JSON, shopEntity.id) //id autonumeric
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_EN, shopEntity.description_en)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION_ES, shopEntity.description_es)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopEntity.latitude)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopEntity.longitude)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.img)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_EN, shopEntity.openingHours_en)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS_ES, shopEntity.openingHours_es)
        return content
    }

    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        return entityFromCursor(cursor)!!
    }

    override fun query(): List<ShopEntity> {
        val  queryResult = ArrayList<ShopEntity>()

        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_TYPE_DATA + " = ? ",
                arrayOf(type.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID)

        while (cursor.moveToNext()){
            val data  = entityFromCursor(cursor)
            if (data != null){
                //add to ArrayList
                queryResult.add(data)
            }
        }
        return queryResult
    }

    fun entityFromCursor(cursor: Cursor) : ShopEntity?{
        //control BREOF & EOF
        if (cursor.isAfterLast || cursor.isBeforeFirst){
            return null
        }

        return ShopEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_SHOP_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_EN)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS_ES)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_ADDRESS))
        )
    }

    override fun queryCursor(id: Long): Cursor {
        return dbReadOnlyConnection.query(DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_TYPE_DATA + " = ? AND " + DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(type.toString(),id.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID)
    }


    override fun update(id: Long, element: ShopEntity): Long {
        return  dbWriteOnlyConnection.update(DBConstants.TABLE_SHOP,
                contentValues(element),
                DBConstants.KEY_SHOP_TYPE_DATA + " = ? AND " + DBConstants.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(type.toString(),id.toString())).toLong()

    }




}