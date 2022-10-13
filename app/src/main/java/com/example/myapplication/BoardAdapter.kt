package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.databinding.ItemBoardBinding

class BoardAdapter(private val list: ArrayList<BoardModel>,private val listener: itemClickListener) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

  inner class BoardViewHolder(private val binding: ItemBoardBinding) :RecyclerView.ViewHolder(binding.root){
    fun onBind(boardModel: BoardModel) {
      binding.imageView.load(boardModel.image)
      binding.nextBtn.text = boardModel.button
      binding.descriptionTv.text = boardModel.description
      binding.nextBtn.setOnClickListener{
       if(adapterPosition == list.size -1) {
         listener.last()
       } else{
         listener.next()
       }
      }
    }

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
  return BoardViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.context),parent,false))

  }

  override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
  holder.onBind(list[position])
  }

  override fun getItemCount(): Int {
   return list.size
  }
}