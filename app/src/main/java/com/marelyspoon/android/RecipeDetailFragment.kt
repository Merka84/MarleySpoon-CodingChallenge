package com.marelyspoon.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marelyspoon.android.databinding.DetailViewBinding
import com.marelyspoon.android.model.Recipe
import com.marelyspoon.android.viewmodel.SingleRecipeViewModel

/**
 * Created on 2019-07-22, 00:28.
 * @author Akram Shokri
 */

class RecipeDetailFragment: Fragment() {
    private var selectedRecipe : Recipe? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DetailViewBinding = DataBindingUtil.inflate(inflater, R.layout.detail_view, container, false)

        binding.viewModel = SingleRecipeViewModel(selectedRecipe ?: Recipe())
        binding.context = context

        return binding.root
    }

    fun setFragmentArg(recipe : Recipe){
        this.selectedRecipe = recipe
    }


}
