package com.toturials.foodviet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.R
import com.toturials.foodviet.entity.TypeFood
import kotlinx.android.synthetic.main.layout_item_choose_type_food.view.*

class TypeFoodAdapter(context: Context, listTypeFood: List<TypeFood>,onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<TypeFoodAdapter.MyViewHolder>() {
    lateinit var context: Context
    lateinit var listTypeFood: List<TypeFood>

    interface OnClickItemListener {
        fun click(type:String)
    }

    lateinit var onClickItemListener: OnClickItemListener

    init {
        this.context = context
        this.listTypeFood = listTypeFood
        this.onClickItemListener = onClickItemListener
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(typeFood: TypeFood) {
            itemView.img_ChooseType.setImageResource(typeFood.bitmap)
            itemView.tv_TypeChooseFood.text = typeFood.type
            if (typeFood.isActived) {
                itemView.bg_ChooseType.setBackgroundResource(R.drawable.custom_bgchoose_food_actived)
                itemView.tv_TypeChooseFood.setTextColor(
                    context.resources.getColor(
                        R.color.colorRed,
                        null
                    )
                )
            } else {
                itemView.bg_ChooseType.setBackgroundResource(R.drawable.custom_bgchoose_food)
                itemView.tv_TypeChooseFood.setTextColor(
                    context.resources.getColor(
                        R.color.colorDarkGray,
                        null
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.layout_item_choose_type_food, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listTypeFood.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listTypeFood[position]
        holder.setData(item)
        holder.itemView.img_ChooseType.setOnClickListener {
            onClickItemListener.click(item.type)
        }
    }
}