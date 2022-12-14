package com.example.dental_surgeon_new

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil
import com.example.dental_surgeon_new.databinding.ActivitySecondBinding
import java.lang.Exception
import java.lang.Math.abs

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    private var motionX = 0f
    private var motionY = 0f

    private var motionX2 = 0f
    private var motionY2 = 0f

    var swapped = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        binding.apply {
            bntCall2page.setOnClickListener {
                val phoneNumber = "0771677010"

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$phoneNumber")
                startActivity(callIntent)
            }

            btnEmail2page.setOnClickListener {
                sendEmail()
            }

            btnOpenmap2page.setOnClickListener {
                location()
            }

            bntWhatsapp2page.setOnClickListener {
                openWhatsApp()
            }
        }
    }

    private fun sendEmail() {
        val email = "arstanbek.rakh@gmail.com"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")
        startActivity(intent)
    }

    private fun openWebsite() {
        val url = "https://neobis.kg"

        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } catch (e: Exception) {
            println(e.message)
        }

    }

    private fun openWhatsApp() {
        val phoneNumber = "0771677010"
        val url = "https://api.whatsapp.com/send?phone=${phoneNumber}"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun location() {
        val location =
            "https://www.google.ru/maps/place/%D1%83%D0%BB" +
                    ".+%D0%90%D0%B1%D0%B4%D1%83%D0%BC%D0%BE%D0%BC%D1%83%" +
                    "D0%BD%D0%BE%D0%B2%D0%B0,+%D0%91%D0%B8%D1%88%D0%BA%D0%B5%D0%BA/" +
                    "@42.8794223,74.605022,17z/data=!3m1!4b1!4m5!3m4!1s0x389eb7c212f05b13:0xf36496e9" +
                    "1b8c32d4!8m2!3d42.8794184!4d74.6072107"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(location)

        startActivity(intent)
    }

    private fun switchToFirstAct() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun switchToThirdAct() {
        val intent = Intent(this, ThirdActivity::class.java)
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
                            switchToFirstAct()
                        } else {
                            switchToThirdAct()
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