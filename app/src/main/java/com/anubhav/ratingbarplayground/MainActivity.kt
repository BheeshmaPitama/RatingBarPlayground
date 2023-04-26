package com.anubhav.ratingbarplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                binding.currentRating.text = rating.toString()
            }
        }

        binding.customRatingBar.setOnRatingChangedListener(listener)
    }
}