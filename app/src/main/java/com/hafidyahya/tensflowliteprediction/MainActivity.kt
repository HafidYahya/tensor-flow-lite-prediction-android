package com.hafidyahya.tensflowliteprediction

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.PredictionHelper
import com.hafidyahya.tensflowliteprediction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var predictionHelper: PredictionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        predictionHelper = PredictionHelper(
            context = this,
            onResult = {result ->
                binding.tvResult.text = result
            },
            onError = {erroMessage ->
                Toast.makeText(this@MainActivity, erroMessage, Toast.LENGTH_SHORT).show()
            }
        )
        binding.btnPredict.setOnClickListener{
            val input = binding.edSales.text.toString()
            predictionHelper.predict(input)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        predictionHelper.close()
    }
}