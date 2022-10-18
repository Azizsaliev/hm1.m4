package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task_model")
data class TaskModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val task : String,
    val date : String,
    val regular : String
):Serializable
