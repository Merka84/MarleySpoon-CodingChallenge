package com.marleyspoon.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marleyspoon.android.databinding.ListViewBinding
import com.marleyspoon.android.model.Recipe
import com.marleyspoon.android.viewmodel.RecipeListViewModel
import com.marleyspoon.android.views.RecipeListAdapter

/**
 * Created on 2019-07-22, 00:39.
 * @author Akram Shokri
 */
class RecipeListFragment : Fragment(){
    private var viewModel : RecipeListViewModel? = null
    private var adapter : RecipeListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ListViewBinding = DataBindingUtil.inflate(inflater, R.layout.list_view, container, false)
       binding.recipeList.layoutManager = LinearLayoutManager(activity)
       binding.loadingProgress.visibility = View.VISIBLE

       viewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
       viewModel?.recipes?.observe(this, Observer<List<Recipe>>{
           adapter = RecipeListAdapter(it, activity)
           binding.recipeList.adapter = adapter
           binding.loadingProgress.visibility = View.GONE
       })

        return binding.root
    }
}