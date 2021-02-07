package com.ipk.todoapp

import android.app.Application
import android.content.Context
import android.content.Intent
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class YapilacaklarAdapter(var mContext:Context,var yapilacaklarListe:ArrayList<Yapilacaklar>,var vt:VtYardimcisi)
    : RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim:View):RecyclerView.ViewHolder(tasarim){
        var satir_card:CardView
        var satir_yazi:TextView
        var satir_resim: Button

        init {
            satir_card = tasarim.findViewById(R.id.satir_card)
            satir_yazi = tasarim.findViewById(R.id.satir_yazi)
            satir_resim = tasarim.findViewById(R.id.satir_resim)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.satir_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var yapilacak = yapilacaklarListe.get(position)

        holder.satir_yazi.text = "${yapilacak.yapilacak_is}"

        holder.satir_resim.setOnClickListener {

            val ad= AlertDialog.Builder(mContext)
            ad.setTitle("Silme İsteği")
            ad.setMessage("${yapilacak.yapilacak_is}\nSilinsin mi?")
            ad.setIcon(R.drawable.satir_icon)
            ad.setPositiveButton("Evet"){ d,i ->
                Snackbar.make(holder.satir_resim, "${yapilacak.yapilacak_is} silindi.", Snackbar.LENGTH_LONG).show()
                YapilacaklarDao().yapilacakSil(vt,yapilacak.yapilacak_id)

                yapilacaklarListe = YapilacaklarDao().tumYapilacaklar(vt)
                notifyDataSetChanged()
            }
            ad.setNegativeButton("Hayır"){ d,i ->
                Snackbar.make(holder.satir_resim, "Silme işlemi iptal edildi!", Snackbar.LENGTH_LONG).show()
            }
            ad.create().show()

        }

        holder.satir_card.setOnClickListener {
            val intent = Intent(mContext,YapilacakDetayActivity::class.java)
            intent.putExtra("nesne",yapilacak)
            mContext.startActivity(intent)
        }
        holder.satir_yazi.setOnClickListener {
            val intent = Intent(mContext,YapilacakDetayActivity::class.java)
            intent.putExtra("nesne",yapilacak)
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return yapilacaklarListe.size
    }
}