package com.marelyspoon.android

import com.marelyspoon.android.model.network.ContentfulHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)

class ContentfulHelperTest {
    @Test
    fun checkFetchMethod() {

        val contentHelper = ContentfulHelper()
        val data = contentHelper.callContentful()

        Assert.assertNotNull(data)
        Assert.assertTrue(data?.size != 0)
        Assert.assertNotNull(data?.get(0))
        Assert.assertNotNull(data?.get(0)?.title)
    }


}

