package com.example.memesharing2
import android.content.Intent
import android.os.Bundle
import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val animDrawable = root_layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(4000)
        animDrawable.start()

        randombt.setOnClickListener{
            val intent = Intent(this, Random::class.java)
            startActivity(intent)
        }
        wholesomebt.setOnClickListener { it: View? ->
            val intent = Intent(this, Wholesome::class.java)
            startActivity(intent)
        }


    }
}
