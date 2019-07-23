package com.marleyspoon.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marleyspoon.android.databinding.DetailViewBinding
import com.marleyspoon.android.model.RecipeData
import com.marleyspoon.android.viewmodel.SingleRecipeViewModel

/**
 * Created on 2019-07-22, 00:28.
 * @author Akram Shokri
 */

class RecipeDetailFragment : Fragment() {
    private lateinit var selectedRecipe: RecipeData
    private lateinit var viewModel: SingleRecipeViewModel
    private val dataKey = "selectedRecipe"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DetailViewBinding = DataBindingUtil.inflate(inflater, R.layout.detail_view, container, false)
        if(savedInstanceState != null){
            selectedRecipe = savedInstanceState.getParcelable(dataKey)
        }
        viewModel = SingleRecipeViewModel(selectedRecipe)
        binding.viewModel = viewModel

        return binding.root

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(dataKey, selectedRecipe)
    }

    fun setFragmentArg(recipe: RecipeData) {
        this.selectedRecipe = recipe
    }


}
