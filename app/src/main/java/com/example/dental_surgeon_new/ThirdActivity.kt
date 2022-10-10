package com.example.dental_surgeon_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import java.lang.Math.abs

class ThirdActivity : AppCompatActivity() {

    private var motionX = 0f
    private var motionY = 0f

    private var motionX2 = 0f
    private var motionY2 = 0f

    var swapped = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    private fun switchToFirstAct() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun switchToSecAct() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (swapped) {
            swapped = false
            return super.onTouchEvent(event)
        }

        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    motionX = event.x
                    motionY = event.y
                }

                MotionEvent.ACTION_UP -> {
                    motionX2 = event.x
                    motionY2 = event.y

                    val valueX = motionX2 - motionX
                    val valueY = motionY2 - motionY

                    if (abs(valueX) > MainActivity.MIN_DISTANCE) {
                        if (motionX < motionX2) {
                            switchToSecAct()
                        } else {
                            switchToFirstAct()
                        }
                    }
                }
            }
        }
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        this.onTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }
}