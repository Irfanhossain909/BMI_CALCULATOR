package com.rootcode.bmicalculator

import android.content.Context
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.rootcode.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val weight = binding.editText.text.toString()
            val height = binding.editText1.text.toString()

            if (validateinput(weight,height)){
                val bmi = weight.toDouble()/((height.toDouble()/100)*(height.toDouble()/100))
                val bmidigit = String.format("%.2f",bmi).toDouble()
                displayResult(bmidigit)
            }
        }
    }

    private fun displayResult(bmi:Double) {
        binding.textView3.text = bmi.toString()
        binding.resulstxt.text = "You Are Helthy"
        binding.text2.text = "(Normal range in 18.5 - 25.5)"

        var result = ""
        var color = 0
        var range = ""

        when{
            bmi < 18.5->{
                result = "Underweight"
                color = R.color.under_weight
                range = "(Underweight range is less then 18.50)"

            }
            bmi in 18.5..24.99->{
                result = "Helthy"
                color = R.color.normal
                range = "(Helthy range is 18.50 to 24.99)"


            }
            bmi in 25.00..29.99->{
                result = "Overweight"
                color = R.color.over_weight
                range = "(Overweight range is 25.00 to 29.99)"

            }
            bmi > 29.99->{
                result = "Obses"
                color = R.color.obses
                range = "(Obses range is greter then 29.99)"

            }
        }
        binding.resulstxt.text = result
        binding.resulstxt.setTextColor(ContextCompat.getColor(this,color))
        binding.text2.text = range
        binding.text2.setTextColor(ContextCompat.getColor(this,color))
    }
    private fun validateinput(weight: String, height: String): Boolean {
        return when {
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is Empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }
        }
    }
}