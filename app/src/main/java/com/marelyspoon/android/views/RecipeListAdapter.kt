package com.marelyspoon.android.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marelyspoon.android.databinding.AdapterListItemBinding
import com.marelyspoon.android.model.Recipe


/**
 * Created on 2019-07-21, 21:32.
 * @author Akram Shokri
 */

class RecipeListAdapter(var list: List<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = AdapterListItemBinding.inflate(layoutInflater, parent, false)

        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class RecipeViewHolder(private val binding: AdapterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            binding.executePendingBindings()
        }

    }
}
