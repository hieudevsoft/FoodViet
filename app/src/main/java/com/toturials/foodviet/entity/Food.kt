package com.toturials.foodviet.entity

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable

data class Food(
    var imageFood: Bitmap?,
    var nameFood: String?, var priceFood: Double, var numberRating: Float, var isLike: Boolean,
    var isFreeShip: Boolean,
    var description: String?,
    var type: String?,
    var restaurant:String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }


    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }
}
