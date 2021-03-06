package com.marleyspoon.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.marleyspoon.android.model.Recipe
import com.marleyspoon.android.model.RecipeData
import com.marleyspoon.android.model.network.ContentfulHelper

/**
 * Created on 2019-07-21, 21:28.
 * @author Akram Shokri
 */

class RecipeListViewModel : ViewModel() {
    val recipes : LiveData<List<RecipeData>> = ContentfulHelper().fetchAllRecipes()

}

class SingleRecipeViewModel(var recipe: RecipeData) : ViewModel()