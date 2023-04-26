package com.anubhav.ratingbarplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.anubhav.ratingbarplayground.customratingbar.CustomRatingBar
import com.anubhav.ratingbarplayground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = object : CustomRatingBar.OnRatingChangedListener{
            override fun onRatingChanged(rating: Int) {
                Toast.makeText(this@MainActivity, "$rating", Toast.LENGTH_SHORT).show()
            }
        }

        binding.customRatingBar.setOnRatingChangedListener(listener)
    }
}