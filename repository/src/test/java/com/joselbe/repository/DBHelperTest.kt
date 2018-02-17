package com.joselbe.repository

import org.junit.Test
import com.joselbe.repository.db.convert
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class DBHelperTest {
    @Test
    @Throws(Exception::class)
    fun given_false_converts_into_0() {
        assertEquals(0, convert(false).toLong())
    }
    fun given_true_converts_into_1() {
        assertEquals(1, convert(true).toLong())
    }


}