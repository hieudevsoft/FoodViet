package com.toturials.foodviet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.toturials.foodviet.R
import com.toturials.foodviet.entity.Food
import com.toturials.foodviet.homeapp.fragmentmainapp.FragmentHomeAppDirections
import com.toturials.foodviet.homeapp.fragmentmainapp.ShowFoodRecommendArgs
import com.toturials.foodviet.homeapp.fragmentmainapp.ShowFoodRecommendDirections
import kotlinx.android.synthetic.main.layout_item_recommendfood.view.*


class FoodRecommendAdapter(context: Context, listFoodRecommend: List<Food>) : RecyclerView.Adapter<FoodRecommendAdapter.MyViewHolder>() {
    lateinit var context: Context
    lateinit var listFoodRecommend: List<Food>
    init {
        this.context = context
        this.listFoodRecommend = listFoodRecommend
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(food: Food)
        {
            setAnimation(itemView.mainCardFoodRecommend)
            itemView.img_FoodRecommend.setImageBitmap(food.imageFood)
            if(food.isLike) itemView.img_LikeFoodRecommend.setImageResource(R.drawable.ic_heart_actived) else
                itemView.img_LikeFoodRecommend.setImageResource(R.drawable.ic_heart)
            itemView.tv_nameFoodRecommend.text = food.nameFood
            itemView.ranting_FoodRecommend.rating = food.numberRating
            itemView.tv_priceFood.text = "$ ${food.priceFood}"
            if(food.isFreeShip) itemView.img_FreeShip.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(
            R.layout.layout_item_recommendfood,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listFoodRecommend.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listFoodRecommend[position]
        holder.setData(item)
        holder.itemView.img_LikeFoodRecommend.setOnClickListener {
            item.isLike=!item.isLike
            notifyDataSetChanged()
        }
        holder.itemView.mainCardFoodRecommend.setOnClickListener {
            val action = FragmentHomeAppDirections.actionFragmentHomeAppToShowFoodRecommend(item)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }
    private fun setAnimation(viewToAnimate: View) {
        // If the bound view wasn't previously displayed on screen, it's animated
            val animation = AnimationUtils.loadAnimation(context, R.anim.anim_food)
            viewToAnimate.startAnimation(animation)

    }
}