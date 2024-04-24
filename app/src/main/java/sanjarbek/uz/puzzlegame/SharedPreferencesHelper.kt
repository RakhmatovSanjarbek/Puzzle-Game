package sanjarbek.uz.puzzlegame

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val mySharedPreferences: SharedPreferences=context.getSharedPreferences("PuzzleGaMe",Context.MODE_PRIVATE)

    fun saveIsPlater(isPlayer:Boolean){
        val editor=mySharedPreferences.edit()
        editor.putBoolean("isPlayer",isPlayer)
        editor.apply()
    }

    fun getIsPlayer():Boolean{
        return mySharedPreferences.getBoolean("isPlayer",true)
    }
}