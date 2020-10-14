package com.toturials.foodviet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.toturials.foodviet.R
import com.toturials.foodviet.entity.Food
import kotlinx.android.synthetic.main.layout_item_recommendfood.view.*
import kotlinx.android.synthetic.main.layout_item_type_food.view.*

class ShowFoodByTypeAdapter( context: Context, listFood: List<Food>) : RecyclerView.Adapter<ShowFoodByTypeAdapter.MyViewHolder>() {
    lateinit var context: Context
    lateinit var listFood: List<Food>
    var lastPosition = -1
    init {
        this.context = context
        this.listFood = listFood
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(food: Food)
        {
            setAnimation(itemView.mainItemFoodByType)
            itemView.img_Food.setImageBitmap(food.imageFood)
            itemView.tv_NameRestaurant.text = food.restaurant
            itemView.tv_nameFood.text = food.nameFood
            itemView.tv_ratingFood.text = food.numberRating.toString()
            itemView.tv_priceFoodTypeFood.text = "$ " +food.priceFood
            if(food.isFreeShip) itemView.img_FreeShipShowTypeFood.visibility = View.VISIBLE else itemView.img_FreeShipShowTypeFood.visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_item_type_food,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listFood[position]
        holder.setData(item)
    }
    private fun setAnimation(viewToAnimate: View) {
        // If the bound view wasn't previously displayed on screen, it's animated
            val animation = AnimationUtils.loadAnimation(context, R.anim.anim_food)
            viewToAnimate.startAnimation(animation)


    }
}