package com.marleyspoon.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marleyspoon.android.databinding.MainActivityBinding
import com.marleyspoon.android.model.RecipeData


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        if (savedInstanceState == null) {
            val fragment = RecipeListFragment()
            createFragment(fragment, binding.container.id)
        }
    }

    fun onItemSelect(recipe: RecipeData) {
        val fragment = RecipeDetailFragment()
        fragment.setFragmentArg(recipe)
        createFragment(fragment, binding.container.id)
    }

    private fun createFragment(fragment: Fragment, resId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(resId, fragment).addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}
