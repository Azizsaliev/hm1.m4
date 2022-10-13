package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
lateinit var binding: FragmentHomeBinding

var taskAdapter = TaskAdapter(arrayListOf())
override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showToolbar()
        initClicker()
        arguments?.let {
            val model = it.getSerializable("model") as TaskModel
            var list = arrayListOf<TaskModel>()
            list.add(model)
            taskAdapter = TaskAdapter(list)
            binding.recycler.adapter = taskAdapter
        }
    }
    private fun initClicker() {
       binding.createFab.setOnClickListener{
        CreateTaskFragment().show(requireActivity().supportFragmentManager,"")
       }
    }
}