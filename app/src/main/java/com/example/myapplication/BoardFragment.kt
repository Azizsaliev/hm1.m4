package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentBoardBinding


class BoardFragment : Fragment(), itemClickListener {

 private lateinit var binding: FragmentBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideToolbar()
        val preferences = requireActivity().getSharedPreferences("setting",Context.MODE_PRIVATE)
        val isShow: Boolean = preferences.getBoolean("isShow",false)
        if (isShow){
           findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
        }

        val list = arrayListOf<BoardModel>()
        list.add(BoardModel(R.drawable.board_first,"Экономь время","Дальше"))
        list.add(BoardModel(R.drawable.board_second,"Достигай целей","Дальше"))
        list.add(BoardModel(R.drawable.board_third,"Развивайся","Начинаем"))
        val boardAdapter = BoardAdapter(list,this)
        binding.viewPager.adapter = boardAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)

    }
    override fun next() {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }
    override fun last() {
        val preferences = requireActivity().getSharedPreferences("setting",Context.MODE_PRIVATE)
        preferences.edit().putBoolean("isShow",true).apply()
        findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
    }

}