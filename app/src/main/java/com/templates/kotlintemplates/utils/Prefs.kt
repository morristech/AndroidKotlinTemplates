package com.templates.kotlintemplates.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Encapsula o acesso ao SharedPreferences
 */
object Prefs {
    val PREF_ID = "livroandroid"

    fun setBoolean(context: Context, flag: String, on: Boolean) {
        val pref = context.getSharedPreferences(PREF_ID, 0)
        val editor = pref.edit()
        editor.putBoolean(flag, on)
        editor.commit()
    }

    fun getBoolean(context: Context, flag: String): Boolean {
        val pref = context.getSharedPreferences(PREF_ID, 0)
        val b = pref.getBoolean(flag, true)
        return b
    }

    fun setInteger(context: Context, flag: String, valor: Int) {
        val pref = context.getSharedPreferences(PREF_ID, 0)
        val editor = pref.edit()
        editor.putInt(flag, valor)
        editor.commit()
    }

    fun getInteger(context: Context, flag: String): Int {
        val pref = context.getSharedPreferences(PREF_ID, 0)
        val i = pref.getInt(flag, 0)
        return i
    }

    fun setString(context: Context, flag: String, valor: String) {
        val pref = context.getSharedPreferences(PREF_ID, 0)
        val editor = pref.edit()
        editor.putString(flag, valor)
        editor.commit()
    }

    fun getString(context: Context, flag: String): String {
        val pref = context.getSharedPreferences(PREF_ID, 0)
        val s = pref.getString(flag, "")
        return s
    }
}

