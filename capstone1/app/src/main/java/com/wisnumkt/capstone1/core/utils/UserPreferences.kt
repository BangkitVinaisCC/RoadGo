package com.wisnumkt.capstone1.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.wisnumkt.capstone1.MainActivity

// Fungsi untuk menyimpan email pengguna
fun saveUserEmail(context: Context, email: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("userEmail", email)
    editor.apply()
}

// Fungsi untuk mendapatkan email pengguna
fun getUserEmail(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("userEmail", null)
}

// Fungsi untuk menghapus email pengguna saat logout
fun clearUserEmail(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.remove("userEmail")
    editor.apply()
}

// Fungsi untuk menyimpan email pengguna
fun saveRekomId(context: Context, id: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("rekomId", id)
    editor.apply()
}

// Fungsi untuk mendapatkan email pengguna
fun getRekomId(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("rekomId", null)
}

// Fungsi untuk menghapus email pengguna saat logout
fun clearRekomIdl(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.remove("rekomId")
    editor.apply()
}

fun recreateMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)

    // Finish the current activity
    (context as? Activity)?.finish()
}