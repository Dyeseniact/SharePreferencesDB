package com.example.sharepreferencesdb

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharepreferencesdb.databinding.ActivityMainBinding
import java.util.prefs.AbstractPreferences


class MainActivity : AppCompatActivity() {


    companion object{
        val PREF_NAME ="package com.example.sharepreferencesdb"
        val STRING = "STRING"
        val NUMBER = "NUMBER"
        val BOOLEAN = "BOOLEAN"
    }

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        setValue()

        binding.button.setOnClickListener {
            saveValues()
        }
    }

    private fun saveValues(){
        val string = binding.etString.text.toString()
        val number = binding.etNumber.text.toString().toFloat()
        val checked = binding.switch1.isChecked

        preferences.edit()
            .putString(STRING, string)
            .putBoolean(BOOLEAN, checked)
            .putFloat(NUMBER, number)
            .apply()
    }

    private fun setValue(){
        val string = preferences.getString(STRING,"")
        val boolean = preferences.getBoolean(BOOLEAN, false)
        val number = preferences.getFloat(NUMBER, 0f)

        with(binding){
            binding.etString.setText(string)
            binding.switch1.isChecked = boolean
            binding.etNumber.setText(number.toString())
        }

    }
}