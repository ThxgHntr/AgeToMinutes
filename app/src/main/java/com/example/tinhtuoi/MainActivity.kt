package com.example.tinhtuoi

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tinhtuoi.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener {
            selectDate()
        }
    }

    private fun selectDate() {
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        val dp = DatePickerDialog(
            this,
            { _, sYear, sMonth, sDayOfMonth ->
                val date = "$sDayOfMonth/${sMonth + 1}/$sYear"
                binding.txtViewDate.text = date
                val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val dob = sdf.parse(date)
                val dateByMinute = dob!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateByMinute = currentDate!!.time / 60000

                val diff = currentDateByMinute - dateByMinute
                binding.txtViewMinutes.text = diff.toString()
            }, year, month, day
        )
        dp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dp.show()
    }
}