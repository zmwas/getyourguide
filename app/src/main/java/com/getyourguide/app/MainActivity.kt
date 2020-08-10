package com.getyourguide.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.getyourguide.app.databinding.ActivityMainBinding
import com.getyourguide.app.reviews.ui.ReviewsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val fragment = ReviewsFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(binding.content.id, fragment)
        transaction.commit()

    }


}
