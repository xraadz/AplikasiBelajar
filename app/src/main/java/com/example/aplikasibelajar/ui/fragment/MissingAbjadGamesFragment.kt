package com.example.aplikasibelajar.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.MissingAbjadGames
import com.example.aplikasibelajar.ui.SummaryActivity
import com.example.aplikasibelajar.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_huruf_games.btn_pilihan1
import kotlinx.android.synthetic.main.fragment_huruf_games.btn_pilihan2
import kotlinx.android.synthetic.main.fragment_huruf_games.btn_pilihan3
import kotlinx.android.synthetic.main.fragment_huruf_games.btn_pilihan4
import kotlinx.android.synthetic.main.fragment_huruf_games.btn_pilihan5
import kotlinx.android.synthetic.main.fragment_huruf_games.btn_pilihan6
import kotlinx.android.synthetic.main.fragment_missingabjad_games.*
import java.util.*

class MissingAbjadGamesFragment : Fragment() {

    companion object {
        fun newInstance() = MissingAbjadGamesFragment()
    }

    private lateinit var sessionManager: SessionManager
    private var i = 0
    private var j = 1
    private var salah = 0
    private var counterSalah = 0
    private var countBenar = 0
    private var list: ArrayList<MissingAbjadGames> = arrayListOf()
    private lateinit var alertDialog: AlertDialog
    private lateinit var rightDialog: AlertDialog
    private lateinit var dialog: View
    private lateinit var btnCobaLagi: Button
    private lateinit var textAnswer: TextView
    private lateinit var jawSalah: MediaPlayer
    private lateinit var jawBenar: MediaPlayer
    private lateinit var ost: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_missingabjad_games, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Deklarasi Session
        sessionManager = SessionManager(view!!.context)
        //Deklarasi Right Dialog
        rightDialog = AlertDialog.Builder(view?.context).create()
        rightDialog.setView(
            LayoutInflater.from(context).inflate(R.layout.alert_dialog_right_answer, null)
        )
        rightDialog.setCancelable(false)
        rightDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //Deklarasi AlertDialog
        alertDialog = AlertDialog.Builder(view?.context).create()
        dialog = LayoutInflater.from(context).inflate(R.layout.alert_dialog_wrong_answer, null)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //AlertDialog ViewBinding
        btnCobaLagi = dialog.findViewById(R.id.btnCobaLagi)
        textAnswer = dialog.findViewById(R.id.textView6)
        btnCobaLagi.setOnClickListener {
            alertDialog.hide()
        }
        //Load and shuffle item
        list.addAll(com.example.aplikasibelajar.data.MissingAbjadGames.listData)
        list.shuffle()
        j = list.size
        //Load Soal
        loadSoal(i)
        //
        jawBenar = MediaPlayer.create(view?.context, R.raw.jawaban_benar)
        jawSalah = MediaPlayer.create(view?.context, R.raw.jawaban_salah)
    }

    private fun loadSoal(i: Int) {
        ost = MediaPlayer.create(view?.context, R.raw.missing_abjad)
        ost.start()
        //Change Image Soal
        img_abjad1?.setImageResource(list[i].soal1)
        img_abjad2?.setImageResource(list[i].soal2)
        img_abjad3?.setImageResource(list[i].soal3)
        img_abjad4?.setImageResource(list[i].soal4)
        img_abjad5?.setImageResource(list[i].soal5)
        //Change Image Jawaban
        btn_pilihan1?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].jaw1) }
        btn_pilihan2?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].jaw2) }
        btn_pilihan3?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].jaw3) }
        btn_pilihan4?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].jaw4) }
        btn_pilihan5?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].jaw5) }
        btn_pilihan6?.background =
            view?.context?.let { ContextCompat.getDrawable(it, list[i].jaw6) }
        //OnClick
        btn_pilihan1.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].kunjaw),
                resources.getResourceEntryName(list[i].jaw1)
            )
        }
        btn_pilihan2.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].kunjaw),
                resources.getResourceEntryName(list[i].jaw2)
            )
        }
        btn_pilihan3.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].kunjaw),
                resources.getResourceEntryName(list[i].jaw3)
            )
        }
        btn_pilihan4.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].kunjaw),
                resources.getResourceEntryName(list[i].jaw4)
            )
        }
        btn_pilihan5.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].kunjaw),
                resources.getResourceEntryName(list[i].jaw5)
            )
        }
        btn_pilihan6.setOnClickListener {
            validation(
                resources.getResourceEntryName(list[i].kunjaw),
                resources.getResourceEntryName(list[i].jaw6)
            )
        }

    }

    private fun validation(answer: String, option: String) {
        ost.stop()
        if (answer == option) {
            //Jika Jawaban Benar
            jawBenar.start()
            sessionManager.putIsInGame(true)
            this@MissingAbjadGamesFragment.i += 1
            countBenar += 1
            //Menampilkan Dialog
            rightDialog.show()
            val t = Timer()
            t.schedule(object : TimerTask() {
                override fun run() {
                    rightDialog.dismiss()
                    t.cancel()
                    Handler(Looper.getMainLooper()).post(Runnable {
                        checkQuestionNumber()
                    })
                }
            }, 2000)
        } else {
            //Jika Jawaban salah
            jawSalah.start()
            salah += 5
            counterSalah += 1
            //Set Text On Alert Dialog
            textAnswer.text = "Jawabannya bukanlah ${option.toUpperCase()}"
            //Show Alert Dialog
            alertDialog.setView(dialog)
            alertDialog.show()
        }
    }

    private fun checkQuestionNumber() {
        if (i == j) {
            var score = 100
            if (salah > score) score = 0
            else score -= salah
            alertDialog.dismiss()
            rightDialog.dismiss()
            if (score == 100)
                sessionManager.putBoolean("Missing", true)
            sessionManager.putBoolean("Abjad", true)
            sessionManager.putIsInGame(false)
            val intent = Intent(view?.context, SummaryActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("jumSalah", counterSalah)
            intent.putExtra("jumBenar", countBenar)
            startActivity(intent)
            activity?.finish()
        } else
            loadSoal(i)
    }

    override fun onPause() {
        super.onPause()
        ost.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        ost.stop()
    }

}