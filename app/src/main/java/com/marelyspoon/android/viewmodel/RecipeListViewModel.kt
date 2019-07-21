package com.marelyspoon.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.marelyspoon.android.model.Recipe
import com.marelyspoon.android.model.network.ContentfulHelper

/**
 * Created on 2019-07-21, 21:28.
 * @author Akram Shokri
 */

class RecipeListViewModel : ViewModel() {

    val recipes : LiveData<List<Recipe>> = ContentfulHelper().fetchAllRecipes()


}