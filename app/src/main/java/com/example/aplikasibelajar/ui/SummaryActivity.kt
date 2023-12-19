package com.example.aplikasibelajar.ui

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.utils.BackgroundServices
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {

    private var scrore = 0
    private var jumSalah = 0
    private var jumBenar = 0
    private lateinit var tap : MediaPlayer
    private lateinit var ost : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        //Load Hasil Skor
        scrore = intent.getIntExtra("score", 100)
        jumSalah = intent.getIntExtra("jumSalah", 0)
        jumBenar = intent.getIntExtra("jumBenar",0)
        //Set Text Berdasarkan Hasil SKor
        txt_totalsalah.text = "$jumSalah Kali"
        txt_totalbenar.text = "$jumBenar Kali"
        txt_Nilai.text = scrore.toString()
        //Mengubah warna text
        if (scrore > 50)
            txt_Nilai.setTextColor(ContextCompat.getColor(this, R.color.Hijau))
        else
            txt_Nilai.setTextColor(ContextCompat.getColor(this, R.color.Merah))
        //Button Finish
        btnFinish.setOnClickListener {
            playSound()
            finish()
        }
        //OST
        ost = MediaPlayer.create(this, R.raw.nilai_keluar)
        ost.start()
    }

    private fun playSound(){
        tap = MediaPlayer.create(this,R.raw.tap_button)
        tap.start()
    }

    override fun onPause() {
        super.onPause()
        val context: Context = applicationContext
        val am =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val taskInfo = am.getRunningTasks(1)
        if (taskInfo.isNotEmpty()) {
            val topActivity = taskInfo[0].topActivity
            if (topActivity!!.packageName != context.packageName) {
                stopService(Intent(applicationContext, BackgroundServices::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(applicationContext, BackgroundServices::class.java))
    }

}