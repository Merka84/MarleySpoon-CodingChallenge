package com.marleyspoon.android.model

/**
 * Created on 2019-07-21, 17:04.
 * @author Akram Shokri
 */

import android.content.Context
import com.contentful.java.cda.CDAAsset
import com.contentful.java.cda.CDAEntry
import com.contentful.java.cda.TransformQuery.ContentfulEntryModel
import com.contentful.java.cda.TransformQuery.ContentfulField
import io.noties.markwon.Markwon

@ContentfulEntryModel("recipe")
data class Recipe(@ContentfulField val title: String,
                  @ContentfulField val tags: List<CDAEntry>,
                  @ContentfulField var chef: String,
                  @ContentfulField val photo: CDAAsset,
                  @ContentfulField val description: String,
                  @ContentfulField val calories: Double
                  ) {
  constructor() : this("",  ArrayList(),  "", CDAAsset(), "",0.0)

    fun getImageUrl() :String{
        if(!photo.url().contains("http")) {
            return "https://${photo.url()}"
        }
        return photo.url()
    }

    fun getFormattedDescription(context: Context) : String {
        return Markwon.create(context).toMarkdown(description).toString()
    }

    fun getFormattedTag(): String{
        var res = ""
        val key = "name"
        if(tags == null){
            return ""
        }

        for(i in 0 until tags.size) {
            res += "#${tags[i].getField<String>(key)}   " //3 spaces at the end of each tag to separate it form the next one
        }
        return res
    }

    fun getFormattedChefName(): String{
        if(chef != null){
            return "By: Chef ${chef}"
        }
        return ""
    }

}

