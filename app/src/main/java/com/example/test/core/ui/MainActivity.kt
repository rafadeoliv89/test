package com.example.test.core.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.features.main.ui.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inflateFragment()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun showLoading(show: Boolean) {
        binding.frameLayoutLoading.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun inflateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerViewMain, MainFragment()).commit()
    }
}