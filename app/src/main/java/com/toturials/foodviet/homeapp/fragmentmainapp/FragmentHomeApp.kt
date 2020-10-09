package com.toturials.foodviet.homeapp.fragmentmainapp

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toturials.foodviet.R
import com.toturials.foodviet.adapter.FoodRecommendAdapter
import com.toturials.foodviet.entity.Food
import com.toturials.foodviet.preferences.FoodViewModel
import kotlinx.android.synthetic.main.fragment_show_food_recommend.view.*
import kotlinx.android.synthetic.main.layout_homefoodviet.*
import kotlinx.android.synthetic.main.layout_homefoodviet.view.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class FragmentHomeApp():Fragment() {
    lateinit var foodRecommendAdapter: FoodRecommendAdapter
    lateinit var listFood:ArrayList<Food>
    lateinit var foodViewModel:FoodViewModel
    val des="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_homefoodviet,container,false)
        initComponents()
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        foodRecommendAdapter = FoodRecommendAdapter(requireContext(),listFood)
        view.recyclerViewRecommendFood.adapter = foodRecommendAdapter
        val layout = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        view.recyclerViewRecommendFood.layoutManager = layout
        foodViewModel.readNumberOfOrder.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            view.layout_Cart.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.anim_cart))
            if(it==0) view.tv_NumberOfOrder.visibility = View.INVISIBLE else view.tv_NumberOfOrder.visibility = View.VISIBLE
            view.tv_NumberOfOrder.text = it.toString()
        })
        view.layout_Cart.setOnClickListener {
            foodViewModel.savedNumberOfOrder(0)
            tv_NumberOfOrder.visibility = View.INVISIBLE
        }
        return view
    }

    private fun initComponents()
    {
        listFood = ArrayList()
        listFood.add(Food(BitmapFactory.decodeResource(resources,R.drawable.asiafood1),"Pizza",5.25,3F,false,false,des,"FAST FOODS"))
        listFood.add(Food(BitmapFactory.decodeResource(resources,R.drawable.popularfood2),"Gà rán",4.25,1.5F,false,false,des,"FOODS"))
        listFood.add(Food(BitmapFactory.decodeResource(resources,R.drawable.asiafood2),"Bánh dâu",5.5,2F,false,true,des,"CAKES"))
        listFood.add(Food(BitmapFactory.decodeResource(resources,R.drawable.banhmi),"Bánh mì",4.5,4.5F,true,false,des,"BREADS"))
        listFood.add(Food(BitmapFactory.decodeResource(resources,R.drawable.popularfood1),"Bánh trôi nước",3.5,2.5F,true,false,des,"VEGETARIAN FOODS"))
        listFood.add(Food(BitmapFactory.decodeResource(resources,R.drawable.popularfood3),"Gà phô mai",1.5,4F,false,false,des,"FOODS"))
        listFood.add(Food(BitmapFactory.decodeResource(resources,R.drawable.popularfood3),"Gà phô mai",2.5,2.5F,true,true,des,"FOODS"))
        Collections.sort(listFood,object:Comparator<Food>{
            override fun compare(o1: Food?, o2: Food?): Int {
                if (o1!!.numberRating>o2!!.numberRating) return -1
                else if (o1.numberRating<o2.numberRating) return 1
                else return 0
            }

        })
    }
}