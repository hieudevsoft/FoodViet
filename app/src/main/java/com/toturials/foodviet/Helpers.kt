package com.toturials.foodviet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import com.toturials.foodviet.entity.Food
import com.toturials.foodviet.entity.TypeFood
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.ArrayList


object Helpers {
    val NUM_PAGES = 3
    fun Activity.hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    fun Activity.showSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
    fun setAnimationY(item: View, duration: Long, delay: Long)
    {
        item.animate().translationY(0F).alpha(1F).setDuration(duration).setStartDelay(delay).start()
    }
    fun setAnimationX(item: View, duration: Long, delay: Long)
    {
        item.animate().translationX(0F).alpha(1F).setDuration(duration).setStartDelay(delay).start()
    }
    fun initAnimationY(item: View, position: Float){
        item.translationY = position
        item.alpha = 0F
    }
    fun initAnimationX(item: View, position: Float){
        item.translationX = position
        item.alpha = 0F
    }
    public fun layoutAnimation(recyclerView: RecyclerView)
    {
        val context = recyclerView.context
        val layoutAnimationController = AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.anim_food
        )
        recyclerView.layoutAnimation = layoutAnimationController
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }
     fun shareBitmap(bitmap: Bitmap, context: Context, typeFood: String, nameFood: String) {


         val cachePath = File(context.externalCacheDir, "my_images/")
         cachePath.mkdirs()
         val file = File(cachePath, "imageCache.png")

             val fileOutputStream = FileOutputStream(file)
             bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
             fileOutputStream.flush()
             fileOutputStream.close()


         //---Share File---//
         //get file uri

         //---Share File---//
         //get file uri
         val myImageFileUri: Uri = FileProvider.getUriForFile(
             context,
             context.applicationContext.packageName + ".provider",
             file
         )
         val intent = Intent(Intent.ACTION_SEND)
         intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
         intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
         intent.putExtra(Intent.EXTRA_STREAM, myImageFileUri)
         intent.putExtra(Intent.EXTRA_SUBJECT, nameFood)
         intent.putExtra(Intent.EXTRA_TEXT, typeFood)
         intent.type = "image/png"
         context.startActivity(Intent.createChooser(intent, "Share With"))
    }
    val listTypeFood = listOf<TypeFood>(
        TypeFood(R.drawable.all_food,"All",false),
        TypeFood(R.drawable.food,"Food",false),
        TypeFood(R.drawable.drinks,"Drinks",false),
        TypeFood(R.drawable.vegetarian_food,"Vegetarian food",false),
        TypeFood(R.drawable.cake,"Cake",false),
        TypeFood(R.drawable.dessert,"Dessert",false),
        TypeFood(R.drawable.homemade,"Homemade",false),
        TypeFood(R.drawable.pavement_food,"Pavement food",false),
        TypeFood(R.drawable.pizza,"Pizza/Burger",false),
        TypeFood(R.drawable.chicken_food,"Chicken food",false),
        TypeFood(R.drawable.pot,"Pot",false),
        TypeFood(R.drawable.sushi,"Sushi",false),
        TypeFood(R.drawable.noodles,"Noodles",false),
        TypeFood(R.drawable.lunch_box,"Lunch box",false)
    )
    fun clickItemTypeFood(type:String,listTypeFood:List<TypeFood>) {
        Log.d("debug", "clickItemTypeFood: ${listTypeFood.size}")
        for (item in listTypeFood)
        {
            item.isActived = item.type==type
            Log.d("debug", "clickItemTypeFood: ${item.isActived} ${item.type}")
        }
    }
    fun filterFoodByType(type:String, listFood: ArrayList<Food>):ArrayList<Food> {
        val listFoodResult = ArrayList<Food>()
        for (item in listFood)
            if(item.type?.toLowerCase(Locale.ROOT)==type.toLowerCase(Locale.ROOT)) listFoodResult.add(item)
        return listFoodResult
    }
}