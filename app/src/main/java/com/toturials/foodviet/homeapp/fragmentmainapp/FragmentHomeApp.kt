package com.toturials.foodviet.homeapp.fragmentmainapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toturials.foodviet.Helpers
import com.toturials.foodviet.R
import com.toturials.foodviet.adapter.FoodRecommendAdapter
import com.toturials.foodviet.adapter.ShowFoodByTypeAdapter
import com.toturials.foodviet.adapter.TypeFoodAdapter
import com.toturials.foodviet.entity.Food
import com.toturials.foodviet.entity.TypeFood
import com.toturials.foodviet.preferences.FoodViewModel
import kotlinx.android.synthetic.main.layout_homefoodviet.*
import kotlinx.android.synthetic.main.layout_homefoodviet.view.*
import java.util.*
import kotlin.collections.ArrayList

class FragmentHomeApp() : Fragment(), TypeFoodAdapter.OnClickItemListener {
    lateinit var foodRecommendAdapter: FoodRecommendAdapter
    lateinit var listFood: ArrayList<Food>
    lateinit var listShowFoodByType: ArrayList<Food>
    lateinit var listTypeFood: ArrayList<TypeFood>
    lateinit var foodViewModel: FoodViewModel
    lateinit var typeFoodAdapter: TypeFoodAdapter
    lateinit var showFoodByTypeAdapter: ShowFoodByTypeAdapter
    val des =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_homefoodviet, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initComponents()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initComponents() {
        initFoodRecommend()
        initTypeFood()
        initShowFoodByType()
        operationsCart()
    }

    private fun operationsCart() {
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        foodViewModel.readNumberOfOrder.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            view?.layout_Cart?.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.anim_cart
                )
            )
            if (it == 0) view?.tv_NumberOfOrder?.visibility =
                View.INVISIBLE else view?.tv_NumberOfOrder?.visibility = View.VISIBLE
            view?.tv_NumberOfOrder?.text = it.toString()
        })
        view?.layout_Cart?.setOnClickListener {
            foodViewModel.savedNumberOfOrder(0)
            tv_NumberOfOrder.visibility = View.INVISIBLE
        }
    }


    private fun initFoodRecommend() {
        listFood = ArrayList()
        listFood.add(
            Food(
                BitmapFactory.decodeResource(resources, R.drawable.asiafood1),
                "Pizza",
                5.25,
                3F,
                false,
                false,
                des,
                "PIZZA/BURGER",
                "Hieu's Restauent"
            )
        )
        listFood.add(
            Food(
                BitmapFactory.decodeResource(resources, R.drawable.popularfood2),
                "Gà rán",
                4.25,
                1.5F,
                false,
                false,
                des,
                "FOOD",
                "Dung Fat"
            )
        )
        listFood.add(
            Food(
                BitmapFactory.decodeResource(resources, R.drawable.asiafood2),
                "Bánh dâu",
                5.5,
                2F,
                false,
                true,
                des,
                "CAKE",
                "Anna Bakery"
            )
        )
        listFood.add(
            Food(
                BitmapFactory.decodeResource(resources, R.drawable.banhmi),
                "Bánh mì",
                4.5,
                4.5F,
                true,
                false,
                des,
                "PAVEMENT FOOD",
                "Anna Bakery"
            )
        )
        listFood.add(
            Food(
                BitmapFactory.decodeResource(resources, R.drawable.popularfood1),
                "Bánh trôi nước",
                3.5,
                2.5F,
                true,
                false,
                des,
                "VEGETARIAN FOOD",
                "Royal City"
            )
        )
        listFood.add(
            Food(
                BitmapFactory.decodeResource(resources, R.drawable.popularfood3),
                "Gà phô mai",
                1.5,
                4F,
                false,
                false,
                des,
                "FOOD",
                "Vin Fast Restaurant"
            )
        )
        listFood.add(
            Food(
                BitmapFactory.decodeResource(resources, R.drawable.popularfood3),
                "Gà phô mai",
                2.5,
                2.5F,
                true,
                true,
                des,
                "FOOD",
                "MC's Donald"
            )
        )
        Collections.sort(listFood, object : Comparator<Food> {
            override fun compare(o1: Food?, o2: Food?): Int {
                if (o1!!.numberRating > o2!!.numberRating) return -1
                else if (o1.numberRating < o2.numberRating) return 1
                else return 0
            }

        })
        foodRecommendAdapter = FoodRecommendAdapter(requireContext(), listFood)
        view?.recyclerViewRecommendFood?.adapter = foodRecommendAdapter
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        view?.recyclerViewRecommendFood?.layoutManager = layout
    }

    private fun initTypeFood() {
        listTypeFood = ArrayList()
        listTypeFood.addAll(Helpers.listTypeFood)
        typeFoodAdapter = TypeFoodAdapter(requireContext(), listTypeFood, this)
        view?.recyclerViewChooseTypeFood?.adapter = typeFoodAdapter
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        view?.recyclerViewChooseTypeFood?.layoutManager = layout
    }

    private fun initShowFoodByType() {
        listShowFoodByType = ArrayList()
        showFoodByTypeAdapter = ShowFoodByTypeAdapter(requireContext(), listShowFoodByType)
        view?.recyclerViewShowChooseTypeFood?.adapter = showFoodByTypeAdapter
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view?.recyclerViewShowChooseTypeFood?.layoutManager = layout
    }

    override fun click(type: String) {
        Helpers.clickItemTypeFood(type, listTypeFood)
        listShowFoodByType.clear()
        if(type.toLowerCase(Locale.ROOT) == "all") {
            listShowFoodByType.addAll(listFood)
            Log.d("TAG", "click: ${listTypeFood.size} ${type.toLowerCase()}")
        }
        else {
            listShowFoodByType.addAll(Helpers.filterFoodByType(type.toLowerCase(Locale.ROOT),listFood))
            Log.d("TAG", "click: ${listShowFoodByType.size} ${type.toLowerCase()} ${listShowFoodByType.toString()}")
        }
        showFoodByTypeAdapter.notifyDataSetChanged()
        typeFoodAdapter.notifyDataSetChanged()
    }
}