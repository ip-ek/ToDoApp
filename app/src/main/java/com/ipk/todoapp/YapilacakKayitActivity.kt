package com.ipk.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_yapilacak_kayit.*

class YapilacakKayitActivity : AppCompatActivity() {

    private lateinit var vt:VtYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yapilacak_kayit)

        toolbarYapilacakKayitActicity.title = "Kişi Kayıt"
        setSupportActionBar(toolbarYapilacakKayitActicity)

        vt = VtYardimcisi(this@YapilacakKayitActivity)

        buttonKaydet.setOnClickListener {
            val yapilacak_is = editTextYapilacakIs.text.toString()

            kayit(yapilacak_is)

        }
    }

    fun kayit(yapilacak_is:String){
        Log.e("Yapilacak Kayıt","$yapilacak_is")

        YapilacaklarDao().yapilacakEkle(vt,yapilacak_is)

        startActivity(Intent(this@YapilacakKayitActivity,MainActivity::class.java))
    }
}