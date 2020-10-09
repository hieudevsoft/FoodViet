package com.toturials.foodviet.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


const val PREF_NAME = "my_pref"

class PreferencesDataStore(context: Context) {
    final val TAG = "PreferencesDataStore"
    companion object Preferences{
        val numberOrder = preferencesKey<Int>("number_order")
    }
    val dataStore = context.createDataStore(
        name = PREF_NAME
    )
    val readNumberOfOrder : Flow<Int> = dataStore.data.catch {
        e->
        if(e is IOException){
            Log.d(TAG, "Exception: ${e.message}")
            emit(emptyPreferences())
        } else throw e
    }.map {preferences->
        val numberOfOrder = preferences[Preferences.numberOrder]?:0
        numberOfOrder
    }


    suspend fun savedNumberOfOrder(number:Int){
        dataStore.edit { preferences->
            preferences[Preferences.numberOrder] = number
        }
    }
}