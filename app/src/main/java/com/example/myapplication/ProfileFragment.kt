package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.myapplication.databinding.FragmentBoardBinding
import com.example.myapplication.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(){
    val IMAGE_CHOOSE = 3
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).binding.toolBarTv.text = "Профиль"
        (requireActivity() as MainActivity).binding.profileImage.load(R.drawable.ic_task)
        (requireActivity() as MainActivity).binding.profileImage.setOnClickListener{
            findNavController().navigate(R.id.homeFragment)
        }
        binding.roundCardView.setOnClickListener{
         val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,IMAGE_CHOOSE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
     if (requestCode == IMAGE_CHOOSE && resultCode == Activity.RESULT_OK){
         binding.roundCardView.setImageURI(data?.data)
     }
    }
}