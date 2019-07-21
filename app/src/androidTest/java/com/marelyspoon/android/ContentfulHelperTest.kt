package com.marelyspoon.android

import com.marelyspoon.android.model.network.ContentfulHelper
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
        contentHelper.fetchAllRecipes()
    }
}
