package com.example.aplikasibelajar.utils

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.aplikasibelajar.R

class BackgroundServices : Service() {

    private lateinit var audio: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        audio = MediaPlayer.create(this, R.raw.ost)
        audio.isLooping = true
    }

    @SuppressLint("WrongConstant")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        audio.start()
        return START_STICKY
    }

    override fun onDestroy() {
        audio.stop()
        audio.release()
    }
}