package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentBoardBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun hideToolbar(){
        binding.toolBar.isVisible = false
    }
    fun showToolbar(){
        binding.toolBar.isVisible = true
    }


}