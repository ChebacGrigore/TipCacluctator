package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateTip.setOnClickListener {
            calculate()
        }

    }

    private fun getPrice() = binding.price.text.toString().toDoubleOrNull() ?: 0.0

    private fun getPercentage(): Double {

        return when (binding.quantityTip.checkedRadioButtonId) {
            binding.twenty.id -> 0.20
            binding.eighteen.id -> 0.18
            else -> 0.15
        }
    }

    private fun isRound() = binding.roundPrice.isChecked

    private fun calculate() {

        var tip = getPrice() * getPercentage()
        if (isRound()) {
            tip = kotlin.math.ceil(tip)
        }
        val formatTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.textAmount.text = getString(R.string.tip_amount_d, formatTip.toString())

    }



}