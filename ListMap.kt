

package com.android.example.foodtrucklocator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ListView
import androidx.fragment.app.FragmentActivity


class ListMap : FragmentActivity() {

  private lateinit var listView: ListView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    

    listView = findViewById<ListView>(R.id.recipe_list_view)

    val recipeList = Recipe.getRecipesFromFile("recipe.json", this)
    val adapter = RecipeAdapter(this, recipeList)
    listView.adapter = adapter

    val context = this
    listView.setOnItemClickListener { _, _, position, _ ->
      val selectedRecipe = recipeList[position]
      val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)
      startActivity(detailIntent)
    }
  }
}



