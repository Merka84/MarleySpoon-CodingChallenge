package com.marelyspoon.android.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.marelyspoon.android.MainActivity
import com.marelyspoon.android.databinding.AdapterListItemBinding
import com.marelyspoon.android.model.Recipe
import com.marelyspoon.android.viewmodel.SingleRecipeViewModel


/**
 * Created on 2019-07-21, 21:32.
 * @author Akram Shokri
 */

class RecipeListAdapter(private val list: List<Recipe>, private val handler: FragmentActivity?) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = AdapterListItemBinding.inflate(layoutInflater, parent, false)

        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(list[position], handler)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RecipeViewHolder(private val binding: AdapterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe, handler: FragmentActivity?) {
            binding.viewModel = SingleRecipeViewModel(recipe)
            if(handler != null) {
                binding.handler = handler as MainActivity
            }
            binding.executePendingBindings()
        }

    }
}
