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
import com.example.aplikasibelajar.ui.fragment.BangunDatarGamesFragment
import com.example.aplikasibelajar.ui.fragment.BangunDatarHomeFragment
import com.example.aplikasibelajar.utils.BackgroundServices
import com.example.aplikasibelajar.utils.SessionManager
import kotlinx.android.synthetic.main.activity_bangun_datar.*

class BangunDatarActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sessionManager: SessionManager
    private lateinit var alertBuilder: AlertDialog.Builder
    private lateinit var tap : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bangun_datar)
        //Load Fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_bangun, BangunDatarHomeFragment.newInstance())
                .commitNow()
        }
        //Set Onclick
        btn_exit.setOnClickListener(this)
        btn_rumah.setOnClickListener(this)
        btn_games.setOnClickListener(this)
        //Session
        sessionManager = SessionManager(this)
        //Alert Builder
        alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Anda sedang dalam game")
        alertBuilder.setMessage("Apakah anda yakin ingin keluar dari game ?")
        alertBuilder.setCancelable(true)
    }

    private fun getInfoGame(): Boolean? {
        return sessionManager.isInGame()
    }

    private fun clearSession() {
        sessionManager.putIsInGame(false)
    }

    private fun playSound(){
        tap = MediaPlayer.create(this,R.raw.tap_button)
        tap.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_exit -> {
                playSound()
                if (getInfoGame() == false) {
                    clearSession()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_bangun, BangunDatarHomeFragment.newInstance())
                        .commitNow()
                } else {
                    alertBuilder.setPositiveButton("Iya") { _, _ ->
                        clearSession()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container_bangun, BangunDatarHomeFragment.newInstance())
                            .commitNow()
                    }
                    alertBuilder.setNegativeButton("Tidak") { _, _ ->
                        //Do Nothing
                    }
                    val mAlertDialog = alertBuilder.create()
                    mAlertDialog.show()
                }
            }
            R.id.btn_games -> {
                playSound()
                if (getInfoGame() == false) {
                    clearSession()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_bangun, BangunDatarGamesFragment.newInstance())
                        .commitNow()
                } else {
                    alertBuilder.setPositiveButton("Iya") { _, _ ->
                        clearSession()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container_bangun, BangunDatarGamesFragment.newInstance())
                            .commitNow()
                    }
                    alertBuilder.setNegativeButton("Tidak") { _, _ ->
                        //Do Nothing
                    }
                    val mAlertDialog = alertBuilder.create()
                    mAlertDialog.show()
                }
            }
            R.id.btn_rumah -> {
                playSound()
                if (getInfoGame() == false) {
                    clearSession()
                    finish()
                } else {
                    alertBuilder.setPositiveButton("Iya") { _, _ ->
                        clearSession()
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
    }

    override fun onBackPressed() {
        if (getInfoGame() == false) {
            clearSession()
            finish()
        } else {
            alertBuilder.setPositiveButton("Iya") { _, _ ->
                clearSession()
                finish()
            }
            alertBuilder.setNegativeButton("Tidak") { _, _ ->
                //Do Nothing
            }
            val mAlertDialog = alertBuilder.create()
            mAlertDialog.show()
        }
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