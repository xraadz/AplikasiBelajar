package com.example.aplikasibelajar.ui

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.aplikasibelajar.R

class MainActivity : AppCompatActivity() {

    lateinit var handler: Handler
    private lateinit var audio: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val top_animation = AnimationUtils.loadAnimation(
            this,
            R.anim.top_animation
        )
        val bot_animation = AnimationUtils.loadAnimation(
            this,
            R.anim.bot_animation
        )

        val image = findViewById<ImageView>(R.id.img_logo)
        val text1 = findViewById<TextView>(R.id.txt1)

        image.animation = top_animation
        text1.animation = bot_animation

        audio = MediaPlayer.create(this, R.raw.loading_screen)
        audio.start()

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, Walktrought::class.java)
            startActivity(intent)
            finish()
        }, 6000)
    }
}