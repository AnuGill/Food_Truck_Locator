package com.android.example.foodtrucklocator

import android.content.Context


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context, private val dataSource: ArrayList<Recipe> ) : BaseAdapter() {


    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    companion object {
        private val LABEL_COLORS = hashMapOf(
                "Latin-american| Caribbean" to R.color.colorLowCarb,
                "American | Italian" to R.color.colorLowFat,
                "Speciality|American" to R.color.colorLowSodium,
                "Asian|Chinese" to R.color.colorMediumCarb,
                "Vegetarian" to R.color.colorVegetarian,
                "Balanced" to R.color.colorBalanced
        )
    }

    override fun getCount(): Int{
        return dataSource.size
    }

    override fun getItem(position: Int): Any{
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long{
        return position.toLong()
    }

    override fun getView(position: Int, convert: View?, parent: ViewGroup): View{
        val rowView = inflater.inflate(R.layout.list_item_recipe, parent, false)

        val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView
        val subtitleTextView = rowView.findViewById(R.id.recipe_list_subtitle) as TextView
        val subtitle2TextView = rowView.findViewById(R.id.recipe_list_price_range) as TextView
        val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView
        val thumbnailImageView = rowView.findViewById(R.id.recipe_list_thumbnail) as ImageView

        val recipe = getItem(position) as Recipe
        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label
        subtitle2TextView.text = recipe.priceRange
        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        val titleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
        titleTextView.typeface = titleTypeFace

        val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_semibolditalic)
        subtitleTextView.typeface = subtitleTypeFace

        val subtitle2TypeFace = ResourcesCompat.getFont(context, R.font.josefinsans_bold)
        subtitle2TextView.typeface = subtitle2TypeFace

        val detailTypeFace = ResourcesCompat.getFont(context, R.font.quicksand_bold)
        detailTextView.typeface = detailTypeFace

        detailTextView.setTextColor(
                ContextCompat.getColor(context, LABEL_COLORS[recipe.label] ?: R.color.colorPrimary))
        return rowView
    }

}