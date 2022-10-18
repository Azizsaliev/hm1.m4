package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentCreateTaskBinding
import com.example.myapplication.databinding.RegularDialogBinding
import com.example.myapplication.room.TaskModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.internal.concurrent.Task
import java.util.*


class CreateTaskFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentCreateTaskBinding
     var task = ""
     var date = ""
     var regular = ""
     var taskModel : TaskModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTaskBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
        if(tag == "update"){
            arguments?.let {
                taskModel = it.getSerializable("model") as TaskModel
                binding.taskEd.setText(taskModel!!.task)
                binding.dateBtn.text = taskModel!!.date
                binding.regularBtn.text = taskModel!!.regular
            }
        }
    }

    private fun showRegularDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = RegularDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.BtnEveryDay.setOnClickListener{
            regular = binding.BtnEveryDay.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
        }
        dialog.show()
        binding.cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initClicker() {
        with(binding){
            applyBtn.setOnClickListener{
                if (tag == "update") {
                    val model = TaskModel(
                        id = taskModel!!.id,
                        task = taskEd.text.toString(),
                        date = dateBtn.text.toString(),
                        regular = regularBtn.text.toString()

                    )
                    App.appDataDataBase.taskDao().update(model)
                } else {
                    val model = TaskModel(task = taskEd.text.toString(), date = date, regular = regular)
                    App.appDataDataBase.taskDao().insert(model)

                }

                dismiss()
            }
            regularBtn.setOnClickListener{
                showRegularDialog()
            }
            dateBtn.setOnClickListener{
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(requireContext(), R.style.CustomDialog, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    dateBtn.text = "$dayOfMonth.${monthOfYear + 1 }.$year"
                    date = dateBtn.text.toString()
                }, year, month, day)
                dpd.show()
            }
        }
    }

}


