package com.marelyspoon.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marelyspoon.android.databinding.ListViewBinding
import com.marelyspoon.android.viewmodel.RecipeListViewModel
import com.marelyspoon.android.views.RecipeListAdapter
import androidx.lifecycle.ViewModelProviders
import com.marelyspoon.android.model.Recipe


class MainActivity : AppCompatActivity() {
    private var viewModel : RecipeListViewModel? = null
    private var adapter : RecipeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ListViewBinding = DataBindingUtil.setContentView(this, R.layout.list_view)
        binding.recipeList.layoutManager = LinearLayoutManager(this)
        binding.loadingProgress.visibility = View.VISIBLE

        viewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
        viewModel?.recipes?.observe(this, Observer<List<Recipe>>{
            adapter = RecipeListAdapter(it)
            binding.recipeList.adapter = adapter
            binding.loadingProgress.visibility = View.GONE
        })

    }
}
