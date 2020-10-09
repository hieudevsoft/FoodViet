package com.toturials.foodviet.homeapp.fragmentmainapp

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.R
import com.toturials.foodviet.entity.Food
import com.toturials.foodviet.preferences.FoodViewModel
import kotlinx.android.synthetic.main.fragment_show_food_recommend.*
import kotlinx.android.synthetic.main.fragment_show_food_recommend.view.*


class ShowFoodRecommend : Fragment() {
    final val TAG = "ShowFoodRecommend"
    lateinit var foodViewModel: FoodViewModel

    var number:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_food_recommend, container, false)
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        arguments?.let {
            val food = ShowFoodRecommendArgs.fromBundle(requireArguments()).Food!!
            view.img_ShowFood.setImageBitmap(food.imageFood)
            view.tv_rating.text = food.numberRating.toString()
            view.tv_PriceShowFood.text = "$"+food?.priceFood.toString()
            if(food.isLike) view.img_LikeShowFood.setImageResource(R.drawable.ic_heart_actived) else view.img_LikeShowFood.setImageResource(R.drawable.ic_heart)
            view.tv_NameShowFood.text = food.nameFood
            view.tv_typeShowFood.text = food.type
            view.tv_DescriptionShowFood.text = food.description
            if(food.isFreeShip) view.img_TagFreeShowFood.visibility = View.VISIBLE
            view.tv_SumPriceShowFood.text = "$"+food.priceFood.toString()
            number = view.textView9.text.toString().toInt()
            view.img_minusShowFood.setOnClickListener {
                if(number >1)
                    number--
                if(number <10)
                {
                    view.textView9.text = "0$number"
                } else view.textView9.text = number.toString()
                view.tv_SumPriceShowFood.text = "$"+(number*food.priceFood).toString()
            }
            view.img_plusShowFood.setOnClickListener {
                number++
                if(number<10)
                {
                    view.textView9.text = "0$number"
                } else view.textView9.text = number.toString()
                view.tv_SumPriceShowFood.text = "$"+(number*food.priceFood).toString()
            }
        }
        view.img_backShowFood.setOnClickListener {
            val action = ShowFoodRecommendDirections.actionShowFoodRecommendToFragmentHomeApp()
            Navigation.findNavController(view).navigate(action)
        }
        view.img_AddCartShowFood.setOnClickListener {
            foodViewModel.readNumberOfOrder.observe(viewLifecycleOwner, Observer {
                number+=it
                Toast.makeText(requireContext(),number.toString(),Toast.LENGTH_SHORT).show()
                foodViewModel.savedNumberOfOrder(number)
                val action = ShowFoodRecommendDirections.actionShowFoodRecommendToFragmentHomeApp()
                Navigation.findNavController(view).navigate(action)
            })
        }
        view.img_ShareShowFood.setOnClickListener {
           view.img_ShowFood.invalidate()
            val drawable = view.img_ShowFood.drawable as BitmapDrawable
            val bitmap = drawable.bitmap
            Helpers.shareBitmap(bitmap,requireContext(),view.tv_NameShowFood.text.toString().trim(),view.tv_typeShowFood.text.toString().trim())
        }
        return view
    }

}