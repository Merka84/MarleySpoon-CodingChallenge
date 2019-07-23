package com.marleyspoon.android.model

/**
 * Created on 2019-07-21, 17:04.
 * @author Akram Shokri
 */

import android.os.Parcel
import android.os.Parcelable
import com.contentful.java.cda.CDAAsset
import com.contentful.java.cda.CDAEntry
import com.contentful.java.cda.TransformQuery.ContentfulEntryModel
import com.contentful.java.cda.TransformQuery.ContentfulField
import com.marleyspoon.android.MainApplication
import io.noties.markwon.Markwon

@ContentfulEntryModel("recipe")
data class Recipe(@ContentfulField val title: String = "",
                  @ContentfulField val tags: List<CDAEntry> = ArrayList(),
                  @ContentfulField var chef: String = "",
                  @ContentfulField val photo: CDAAsset = CDAAsset(),
                  @ContentfulField val description: String = "",
                  @ContentfulField val calories: Double = 0.0
                  ) {
  //constructor() : this("",  ArrayList(),  "", CDAAsset(), "",0.0)

    fun getImageUrl() :String{
        if(!photo.url().contains("http")) {
            return "https://${photo.url()}"
        }
        return photo.url()
    }

    fun getFormattedDescription() : String {
        return Markwon.create(MainApplication.mainApplicationContext()).toMarkdown(description).toString()
    }

    fun getTagList(): List<String>{
        var res = ArrayList<String>()
        val key = "name"
        if(tags == null){
            return res
        }

        for(i in 0 until tags.size) {
            res.add(tags[i].getField<String>(key))
        }
        return res
    }




}


data class RecipeData(@ContentfulField val title: String = "",
                      @ContentfulField val tags: List<String> = ArrayList(),
                      @ContentfulField var chef: String? = "",
                      @ContentfulField val photoUrl: String = "",
                      @ContentfulField val description: String = "",
                      @ContentfulField val calories: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    )

    constructor(recipe: Recipe) : this(recipe.title, recipe.getTagList(), recipe.chef, recipe.getImageUrl(), recipe.getFormattedDescription(), recipe.calories)

    fun getFormattedChefName(): String{
        if(chef != null){
            return "By: Chef ${chef}"
        }
        return ""
    }

    fun getFormattedTag(): String{
        var res = ""
        if(tags == null){
            return ""
        }

        for(i in 0 until tags.size) {
            res += "#${tags[i]}   " //3 spaces at the end of each tag to separate it form the next one
        }
        return res
    }

    companion object CREATOR : Parcelable.Creator<RecipeData> {
        override fun createFromParcel(parcel: Parcel): RecipeData {
            return RecipeData(parcel)
        }

        override fun newArray(size: Int): Array<RecipeData?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeStringList(tags)
        dest?.writeString(chef)
        dest?.writeString(photoUrl)
        dest?.writeString(description)
        dest?.writeDouble(calories)
    }

    override fun describeContents(): Int {
        return 0
    }
}

