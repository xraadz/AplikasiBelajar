package com.example.aplikasibelajar.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.utils.BackgroundServices
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        //StopService
        stopService(Intent(this,BackgroundServices::class.java))
        //Initialize Youtube
        val video = intent.getStringExtra("video")
        ytPlayer.initialize(
            "AIzaSyDmm8CBtJu8-Ulan7BPNgebsFygAH46V9M",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Terjadi Kesalahan, Periksa Koneksi Anda!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1!!.loadVideo(video)
                }
            })
    }
}