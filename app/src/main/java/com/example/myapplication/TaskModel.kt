package com.example.myapplication

import java.io.Serializable

data class TaskModel (
    val task : String,
    val date : String,
    val regular : String
):Serializable
