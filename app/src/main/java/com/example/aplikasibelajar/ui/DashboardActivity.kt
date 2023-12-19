package com.example.aplikasibelajar.ui

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.ui.fragment.HomeFragment
import com.example.aplikasibelajar.ui.fragment.YoutubeFragment
import com.example.aplikasibelajar.utils.BackgroundServices
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashboardActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var audio: MediaPlayer
    private lateinit var audioResume: MediaPlayer
    private lateinit var tap: MediaPlayer
    private lateinit var alertBuilder: AlertDialog.Builder
    private var flag = false
    private var firstRun = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
        //Set OnClick Listener
        btn_rumah.setOnClickListener(this)
        btn_youtube.setOnClickListener(this)
        imageButton.setOnClickListener(this)
        //start soundtrack
        startService(Intent(applicationContext, BackgroundServices::class.java))
        audio = MediaPlayer.create(this, R.raw.home)
        audio.start()
        //Alert Builder
        alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Anda sedang dalam game")
        alertBuilder.setMessage("Apakah anda yakin ingin keluar dari game ?")
        alertBuilder.setCancelable(true)
    }

    private fun playSound() {
        tap = MediaPlayer.create(this, R.raw.tap_button)
        tap.start()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_rumah -> {
                playSound()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow()
            }
            R.id.btn_youtube -> {
                playSound()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, YoutubeFragment.newInstance())
                    .commitNow()
            }
            R.id.imageButton -> {
                playSound()
                alertBuilder.setPositiveButton("Iya") { _, _ ->
                    finish()
                }
                alertBuilder.setNegativeButton("Tidak") { _, _ ->
                    //Do Nothing
                }
                val mAlertDialog = alertBuilder.create()
                mAlertDialog.show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        audio.stop()
        if(firstRun)
           firstRun = false
        else
            audioResume.stop()
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

    override fun onBackPressed() {
        alertBuilder.setPositiveButton("Iya") { _, _ ->
            finish()
        }
        alertBuilder.setNegativeButton("Tidak") { _, _ ->
            //Do Nothing
        }
        val mAlertDialog = alertBuilder.create()
        mAlertDialog.show()

    }

    override fun onResume() {
        super.onResume()
        if (!flag)
            flag = true
        else {
            audioResume = MediaPlayer.create(this, R.raw.pilih_permainan)
            audioResume.start()
        }
        startService(Intent(applicationContext, BackgroundServices::class.java))
    }
}