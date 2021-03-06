package com.marleyspoon.android.model.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.contentful.java.cda.CDAClient
import com.contentful.java.cda.CDAEntry
import com.contentful.java.cda.LocalizedResource
import com.marleyspoon.android.model.Recipe
import com.marleyspoon.android.model.RecipeData
import java.util.concurrent.Executors


/**
 * Created on 2019-07-21, 14:41.
 * @author Akram Shokri
 */

class ContentfulHelper {
    private val SPACE_ID = "kk2bw5ojx476"
    private val TOKEN = "7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c"
    private val client = contentDeliveryClient()
    private val executor = Executors.newSingleThreadExecutor()

    private var recipeLiveData: MutableLiveData<List<RecipeData>> = MutableLiveData()

    fun contentDeliveryClient(): CDAClient {
        return CDAClient.builder()
            .setSpace(SPACE_ID)
            .setToken(TOKEN)
            .build()
    }

    fun fetchAllRecipes(): LiveData<List<RecipeData>> {
        executor.execute {
            val data = callContentful()
            recipeLiveData.postValue(data)
        }

        return recipeLiveData
    }

    fun callContentful(): List<RecipeData> {
        val data = client.observeAndTransform(Recipe::class.java)
            .where("content_type", "recipe")
            .include(1)
            .all().blockingFirst() as List<Recipe>

        //This ugly below code is to set 'chef' value as I couldn't figure why above request can't set it.
        //It is hacky as recipe title are not necessarily unique and it will result in showing wrong chef name. :(
        //FIXME: solve above problem, preferably with one request
        val tmp = client.fetch(CDAEntry::class.java)
            .withContentType("recipe")
            .select("fields.chef")
            .select("fields.title")
            .include(1)
            .all()
        for (i in 0 until tmp.items().size) {
            val chefName =
                (tmp.items()[i] as LocalizedResource)?.getField<CDAEntry>("en-US", "chef")?.getField<String>("name")
            val recipeTitle = (tmp.items()[i] as LocalizedResource)?.getField<String>("en-US", "title")
            if (chefName != null) {
                data.find { it.title == recipeTitle }?.chef = chefName
            }
        }

        val recipeDataList = ArrayList<RecipeData>()
        for(i in 0 until data.size){
            recipeDataList.add(RecipeData(data[i]))
        }
        return recipeDataList
    }


}