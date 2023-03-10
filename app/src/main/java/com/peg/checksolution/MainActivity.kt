package com.peg.checksolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.peg.checksolution.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val signs = arrayOf("+","-", "*","/")
    var left = 0
    var right = 0
    var sign = "+"
    var trueCount = 0
    var allCount = 0
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun Start(view: View){

        left = (10..99).random()
        right = (10..99).random()
        sign = signs.random()
        if(sign == "/"){
            left = left * right
        }
        binding.editText.isEnabled = true
        binding.textViewLeftOperand.text = left.toString()
        binding.textViewRightOperand.text = right.toString()
        binding.textViewSign.text = sign
        binding.buttonStart.isEnabled = false
        binding.buttonCheck.isEnabled = true
        binding.editText.setText("")
        binding.linearLayoutExample.setBackgroundColor(getColor(R.color.white))
    }
    fun Check(view: View){
        if(binding.editText.text.isNullOrEmpty()){
            Toast.makeText(this,getString(R.string.editText), Toast.LENGTH_LONG).show()
            return
        }
        var check = false
        val userAnswer = binding.editText.text.toString().toInt()
        when(sign){
            "+" -> {
                if(userAnswer == (left + right)){
                    trueCount+=1
                    check = true
                }
            }
            "-" -> {
                if(userAnswer == (left - right)){
                    trueCount+=1
                    check = true

                }
            }
            "*" -> {
                if(userAnswer == (left * right)){
                    trueCount+=1
                    check = true
                }
            }
            "/" -> {
                if(userAnswer == (left / right)){
                    trueCount+=1
                    check = true
                }
            }
        }
        if(check){
            binding.linearLayoutExample.setBackgroundColor(getColor(R.color.trueColor))
        }
        else{
            binding.linearLayoutExample.setBackgroundColor(getColor(R.color.falseColor))
        }
        allCount+=1
        binding.buttonStart.isEnabled = true
        binding.buttonCheck.isEnabled = false
        binding.editText.isEnabled = false
        Validate()
    }

    fun Validate(){
        binding.textViewTrue.text = trueCount.toString()
        binding.textViewFalse.text = (allCount - trueCount).toString()
        binding.textViewAllCount.text = allCount.toString()
        binding.textViewPercent.text = "${String.format("%.2f",(100f / allCount.toFloat() * trueCount.toFloat()))}%"
    }
}