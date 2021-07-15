//┌─────────────────────────────────────────────────────────────────────────┐
//│   Created by sarang_smk on 16-Jul-21                                    │
//│   https://github.com/sarangsmk                                          │
//│   Copyright (c) 2021 http://smktech.ml/ .All rights reserved.           │
//└─────────────────────────────────────────────────────────────────────────┘
package com.example.coloranimation

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Guideline
import kotlinx.coroutines.*
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ll = findViewById<LinearLayout>(R.id.ll)
        val gLine = findViewById<Guideline>(R.id.gline)

        GlobalScope.launch(context = Dispatchers.Main) {
            colorChange(ll)
        }
        GlobalScope.launch(context = Dispatchers.Main) {
            heightChange(gLine)
        }

        //TODO: Match/Change the color based on height
    }

    //Change Height
    suspend fun heightChange(gLine: Guideline){
        var height = 1.0
        var change = 0.0
        while(true)
        {
            //Change height back and forth
            if(height <= 0.0)
            {
                change = 0.001
            }else if(height == 1.0){
                change = -0.001
            }
            gLine.setGuidelinePercent(height.toFloat())
            height += change
            delay(1L) //Movement speed
        }
    }

    //Gradient color change
    suspend fun colorChange(ll : LinearLayout){
        val colors = arrayListOf("#FF0000","#FF4D00","#FF8200","#FFA600","#FFDC00","#FFE600","#FFF800","#D5FF00","#72F600","#07FB00")
       var index = 0
        while(true)
       {
           //infinite loop through the colors
           index = if(index == colors.size) 0 else index

           val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
               intArrayOf(Color.WHITE,Color.parseColor(colors[index])))
           ll.setBackground(gd)

           kotlinx.coroutines.delay(300L)
           index++
       }
    }

//Delay or Wait
    suspend fun delay(length: Long) = withContext(Dispatchers.IO){
        Thread.sleep(length)
    }
}