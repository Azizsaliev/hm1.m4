package com.example.myapplication

import com.example.myapplication.room.TaskModel

interface TaskItemClick {
    fun itemClick(taskModel: TaskModel)
    fun deleteItemClick(taskModel: TaskModel)
}