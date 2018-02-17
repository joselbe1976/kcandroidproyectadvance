package com.joselbe.repository

import android.util.Log
import com.joselbe.repository.model.ShopEntity
import com.joselbe.repository.model.ShopRepsonseEntity
import com.joselbe.repository.network.json.JsonEntityParse
import com.joselbe.repository.util.ReadJsonFile
import junit.framework.Assert.*
import org.junit.Test


class JSONParsingTest {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_correcty() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(!shopsJson.isEmpty())

        //hacemos parsing JSON

        val parser = JsonEntityParse()
        val shop  = parser.parse<ShopEntity>(shopsJson)
        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)

    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_error() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(!shopsJson.isEmpty())

        //hacemos parsing JSON

        val parser = JsonEntityParse()
        val shop  = parser.parse<ShopEntity>(shopsJson)
        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)

    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_containing_json_it_parses_wrong_latitud_parse_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")
        assertTrue(!shopsJson.isEmpty())

        //hacemos parsing JSON

        val parser = JsonEntityParse()
        val responseEntity  = parser.parse<ShopRepsonseEntity>(shopsJson)


        assertNotNull(responseEntity)
        assertEquals(6, responseEntity.result.count())
        assertEquals("Cortefiel - Preciados", responseEntity.result[0].name)

    }

}