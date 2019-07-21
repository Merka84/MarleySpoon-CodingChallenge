package com.marelyspoon.android.model.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.contentful.java.cda.CDAArray
import com.contentful.java.cda.CDACallback
import com.contentful.java.cda.CDAClient
import com.marelyspoon.android.model.Recipe
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * Created on 2019-07-21, 14:41.
 * @author Akram Shokri
 */

class ContentfulHelper {
    val SPACE_ID = "kk2bw5ojx476"
    val TOKEN = "7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c"
    val client = contentDeliveryClient()
    val executor = Executors.newSingleThreadExecutor()

    var recipeLiveData: MutableLiveData<List<Recipe>> = MutableLiveData()
        private set

    /*   val count = client
           .fetch(CDAEntry::class.java)
           .limit(0)
           .all()
           .total()
   */

    fun contentDeliveryClient(): CDAClient {
        return CDAClient.builder()
            .setSpace(SPACE_ID)
            .setToken(TOKEN)
            .build()
    }

    fun fetchAllRecipes(): LiveData<List<Recipe>> {
       /* val tmp = client.observeAndTransform(Recipe::class.java)
            .where("content_type", "recipe")
            .include(1)
            .all().blockingFirst() as List<Recipe>*/

        executor.execute {
            val tmp= client.observeAndTransform(Recipe::class.java)
                .where("content_type", "recipe")
                .include(1)
                .all().blockingFirst() as List<Recipe>

            recipeLiveData.postValue(tmp)
        }


        return recipeLiveData
    }

    /*
    client.fetch(CDAEntry.class)
        .include(3)
         .where("content_type", "recipe")
                .all();

//        val resources = ArrayList<CDAResource>()
//        val temp = client.fetch(CDAEntry::class.java)
//            .where("content_type", "recipe")
//            .include(1)
//            .all()

     */

}