package com.marelyspoon.android.model

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
                  @ContentfulField val tags: List<String>,
                  @ContentfulField val chef: CDAEntry,
                  @ContentfulField val photo: CDAAsset,
                  @ContentfulField val description: String,
                  @ContentfulField val calories: Double
                  ) {
  constructor() : this("",  ArrayList(),  CDAEntry(), CDAAsset(), "",0.0)

    fun getImageUrl() :String{
        if(!photo.url().contains("http")) {
            return "https://${photo.url()}"
        }
        return photo.url()
    }

    fun getFormattedDescription(context: Context) : String {
        val markwon = Markwon.create(context).toMarkdown(description).toString()
        return markwon
    }
}

