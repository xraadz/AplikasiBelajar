package com.example.aplikasibelajar.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.models.Warna

class AdapterWarna(val listWarna: ArrayList<Warna>) :
    RecyclerView.Adapter<AdapterWarna.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_warna, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (nama, kode, audio) = listWarna[position]
        holder.audioWarna = MediaPlayer.create(holder.itemView.context, audio)
        holder.btnWarna.backgroundTintList =
            ContextCompat.getColorStateList(holder.itemView.context, kode)
        holder.txtWarna.text = nama
        holder.txtWarna.setTextColor(ContextCompat.getColor(holder.itemView.context, kode))
        holder.btnWarna.setOnClickListener {
            holder.audioWarna.start()
        }
        //Pengecekan warna, untuk mengubah warna teks kuning dan putih
        if (nama == "Kuning" || nama == "Putih")
            holder.txtWarna.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.hitam
                )
            )

    }

    override fun getItemCount(): Int {
        return listWarna.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btnWarna: Button = itemView.findViewById(R.id.btnWarna)
        var txtWarna: TextView = itemView.findViewById(R.id.txt_warna)
        lateinit var audioWarna: MediaPlayer
    }
}
